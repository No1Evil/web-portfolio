package dev.tsumakov.application.core.skill.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.core.skill.dto.in.UpdateSkillDto;
import dev.tsumakov.application.core.skill.dto.outer.SkillDto;
import dev.tsumakov.application.core.skill.exception.SkillNotFoundException;
import dev.tsumakov.application.core.skill.mapper.SkillDtoMapper;
import dev.tsumakov.domain.core.skill.model.Skill;
import dev.tsumakov.domain.core.skill.repository.SkillRepository;
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
class UpdateSkillUseCaseImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.now();

  @Mock
  private SkillRepository repository;
  @Mock
  private SkillDtoMapper mapper;

  private UpdateSkillUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new UpdateSkillUseCaseImpl(repository, mapper);
  }

  private Skill currentSkill() {
    return new Skill(1, 1, "Java", "icon.svg", NOW, NOW, 3L);
  }

  @Test
  void shouldThrowNotFoundExceptionWhenSkillMissing() {
    when(repository.findById(99)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> useCase.execute(new UpdateSkillDto(99, null, null, null)))
        .isInstanceOf(SkillNotFoundException.class)
        .hasMessage("Skill with id 99 not found");

    verify(repository, never()).update(any());
  }

  @Test
  void shouldUpdateAllProvidedFieldsWithIncrementedVersion() {
    var command = new UpdateSkillDto(1, 2, "Kotlin", "kotlin.svg");
    var saved = new Skill(1, 2, "Kotlin", "kotlin.svg", NOW, NOW, 4L);
    var expectedDto = new SkillDto(1, 2, "Kotlin", "kotlin.svg", NOW, NOW);

    when(repository.findById(1)).thenReturn(Optional.of(currentSkill()));
    when(repository.update(any())).thenReturn(saved);
    when(mapper.toDto(saved)).thenReturn(expectedDto);

    var result = useCase.execute(command);

    assertThat(result).isEqualTo(expectedDto);

    ArgumentCaptor<Skill> captor = ArgumentCaptor.forClass(Skill.class);
    verify(repository).update(captor.capture());
    var updated = captor.getValue();
    assertThat(updated.id()).isEqualTo(1);
    assertThat(updated.categoryId()).isEqualTo(2);
    assertThat(updated.name()).isEqualTo("Kotlin");
    assertThat(updated.iconUrl()).isEqualTo("kotlin.svg");
    assertThat(updated.version()).isEqualTo(3L);
  }

  @Test
  void shouldNotCallUpdateWhenNoFieldProvided() {
    var command = new UpdateSkillDto(1, null, null, null);
    var current = currentSkill();
    var expectedDto = new SkillDto(1, 1, "Java", "icon.svg", NOW, NOW);

    when(repository.findById(1)).thenReturn(Optional.of(current));
    when(mapper.toDto(current)).thenReturn(expectedDto);

    var result = useCase.execute(command);

    assertThat(result).isEqualTo(expectedDto);
    verify(repository, never()).update(any());
  }

  @Test
  void shouldPartiallyUpdateOnlyProvidedFields() {
    var command = new UpdateSkillDto(1, null, "Kotlin", null);
    var current = currentSkill();

    when(repository.findById(1)).thenReturn(Optional.of(current));
    when(repository.update(any())).thenReturn(current.updateName("Kotlin"));
    when(mapper.toDto(any())).thenReturn(new SkillDto(1, 1, "Kotlin", null, NOW, NOW));

    useCase.execute(command);

    ArgumentCaptor<Skill> captor = ArgumentCaptor.forClass(Skill.class);
    verify(repository).update(captor.capture());
    var updated = captor.getValue();
    assertThat(updated.name()).isEqualTo("Kotlin");
    assertThat(updated.categoryId()).isEqualTo(1);
    assertThat(updated.iconUrl()).isEqualTo("icon.svg");
    assertThat(updated.version()).isEqualTo(3L);
  }

  @Test
  void shouldPropagateDomainValidationExceptionWhenNameIsBlank() {
    var command = new UpdateSkillDto(1, null, "  ", null);

    when(repository.findById(1)).thenReturn(Optional.of(currentSkill()));

    assertThatThrownBy(() -> useCase.execute(command))
        .isInstanceOf(DomainValidationException.class);

    verify(repository, never()).update(any());
  }
}