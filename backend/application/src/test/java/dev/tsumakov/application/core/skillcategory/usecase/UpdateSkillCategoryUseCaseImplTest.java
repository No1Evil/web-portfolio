package dev.tsumakov.application.core.skillcategory.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.core.skillcategory.dto.in.UpdateSkillCategoryDto;
import dev.tsumakov.application.core.skillcategory.dto.outer.SkillCategoryDto;
import dev.tsumakov.application.core.skillcategory.exception.SkillCategoryNotFoundException;
import dev.tsumakov.application.core.skillcategory.mapper.SkillCategoryDtoMapper;
import dev.tsumakov.domain.core.skillcategory.model.SkillCategory;
import dev.tsumakov.domain.core.skillcategory.repository.SkillCategoryRepository;
import dev.tsumakov.domain.shared.exception.DomainValidationException;
import java.time.OffsetDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateSkillCategoryUseCaseImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.now();

  @Mock
  private SkillCategoryRepository repository;
  @Mock
  private SkillCategoryDtoMapper mapper;

  private UpdateSkillCategoryUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new UpdateSkillCategoryUseCaseImpl(repository, mapper);
  }

  private SkillCategory currentCategory() {
    return new SkillCategory(1, "Backend", "icon.svg", 2L, NOW, NOW);
  }

  @Test
  void shouldThrowNotFoundExceptionWhenCategoryMissing() {
    when(repository.findById(99)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> useCase.execute(new UpdateSkillCategoryDto(99, "X", null)))
        .isInstanceOf(SkillCategoryNotFoundException.class)
        .hasMessage("Skill category with id 99 not found");

    verify(repository, never()).update(any());
  }

  @Test
  void shouldUpdateAllProvidedFieldsWithIncrementedVersion() {
    var command = new UpdateSkillCategoryDto(1, "Frontend", "new-icon.svg");
    var current = currentCategory();
    var saved = new SkillCategory(1, "Frontend", "new-icon.svg", 3L, NOW, NOW);
    var expectedDto = new SkillCategoryDto(1, "Frontend", "new-icon.svg", NOW, NOW);

    when(repository.findById(1)).thenReturn(Optional.of(current));
    when(repository.update(any())).thenReturn(saved);
    when(mapper.toDto(saved)).thenReturn(expectedDto);

    var result = useCase.execute(command);

    assertThat(result).isEqualTo(expectedDto);

    ArgumentCaptor<SkillCategory> captor = ArgumentCaptor.forClass(SkillCategory.class);
    verify(repository).update(captor.capture());
    var updated = captor.getValue();
    assertThat(updated.name()).isEqualTo("Frontend");
    assertThat(updated.iconUrl()).isEqualTo("new-icon.svg");
    assertThat(updated.version()).isEqualTo(3L);
    assertThat(updated.id()).isEqualTo(1);
    assertThat(updated.createdAt()).isEqualTo(NOW);
  }

  @Test
  void shouldNotCallUpdateWhenNoFieldProvided() {
    var command = new UpdateSkillCategoryDto(1, null, null);
    var current = currentCategory();
    var expectedDto = new SkillCategoryDto(1, "Backend", "icon.svg", NOW, NOW);

    when(repository.findById(1)).thenReturn(Optional.of(current));
    when(mapper.toDto(current)).thenReturn(expectedDto);

    var result = useCase.execute(command);

    assertThat(result).isEqualTo(expectedDto);
    verify(repository, never()).update(any());
  }

  @Test
  void shouldPartiallyUpdateOnlyProvidedField() {
    var command = new UpdateSkillCategoryDto(1, "Frontend", null);
    var current = currentCategory();

    when(repository.findById(1)).thenReturn(Optional.of(current));
    when(repository.update(any())).thenReturn(current.updateName("Frontend"));
    when(mapper.toDto(any())).thenReturn(new SkillCategoryDto(1, "Frontend", "icon.svg", NOW, NOW));

    useCase.execute(command);

    ArgumentCaptor<SkillCategory> captor = ArgumentCaptor.forClass(SkillCategory.class);
    verify(repository).update(captor.capture());
    var updated = captor.getValue();
    assertThat(updated.name()).isEqualTo("Frontend");
    assertThat(updated.iconUrl()).isEqualTo("icon.svg");
    assertThat(updated.version()).isEqualTo(3L);
  }

  @Test
  void shouldPropagateDomainValidationExceptionWhenNameIsBlank() {
    var command = new UpdateSkillCategoryDto(1, "   ", null);
    var current = currentCategory();

    when(repository.findById(1)).thenReturn(Optional.of(current));

    assertThatThrownBy(() -> useCase.execute(command))
        .isInstanceOf(DomainValidationException.class);

    verify(repository, never()).update(any());
  }
}