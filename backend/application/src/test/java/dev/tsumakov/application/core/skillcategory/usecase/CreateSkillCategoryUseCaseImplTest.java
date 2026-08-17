package dev.tsumakov.application.core.skillcategory.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.core.skillcategory.dto.in.CreateSkillCategoryDto;
import dev.tsumakov.application.core.skillcategory.dto.outer.SkillCategoryDto;
import dev.tsumakov.application.core.skillcategory.mapper.SkillCategoryDtoMapper;
import dev.tsumakov.domain.core.skillcategory.factory.SkillCategoryFactory;
import dev.tsumakov.domain.core.skillcategory.model.SkillCategory;
import dev.tsumakov.domain.core.skillcategory.repository.SkillCategoryRepository;
import dev.tsumakov.domain.shared.exception.DomainValidationException;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateSkillCategoryUseCaseImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.now();

  @Mock
  private SkillCategoryFactory factory;
  @Mock
  private SkillCategoryRepository repository;
  @Mock
  private SkillCategoryDtoMapper mapper;

  private CreateSkillCategoryUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new CreateSkillCategoryUseCaseImpl(factory, repository, mapper);
  }

  @Test
  void shouldCreateCategoryAndReturnDto() {
    var command = new CreateSkillCategoryDto("Backend", "icon.svg");
    var freshCategory = new SkillCategory(null, "Backend", "icon.svg", 1L, NOW, NOW);
    var savedCategory = new SkillCategory(1, "Backend", "icon.svg", 1L, NOW, NOW);
    var expectedDto = new SkillCategoryDto(1, "Backend", "icon.svg", NOW, NOW);

    when(factory.createNew(command.name(), command.iconUrl())).thenReturn(freshCategory);
    when(repository.create(freshCategory)).thenReturn(savedCategory);
    when(mapper.toDto(savedCategory)).thenReturn(expectedDto);

    var result = useCase.execute(command);

    assertThat(result).isEqualTo(expectedDto);
    verify(factory).createNew(command.name(), command.iconUrl());
    verify(repository).create(freshCategory);
    verify(mapper).toDto(savedCategory);
  }

  @Test
  void shouldPassCommandFieldsToFactory() {
    var command = new CreateSkillCategoryDto("Frontend", null);
    var created = new SkillCategory(null, "Frontend", null, 1L, NOW, NOW);

    when(factory.createNew("Frontend", null)).thenReturn(created);
    when(repository.create(created)).thenReturn(created);
    when(mapper.toDto(created)).thenReturn(new SkillCategoryDto(null, "Frontend", null, NOW, NOW));

    useCase.execute(command);

    verify(factory).createNew("Frontend", null);
  }

  @Test
  void shouldPropagateDomainValidationExceptionAndNotSave() {
    var command = new CreateSkillCategoryDto(null, "icon.svg");

    when(factory.createNew(null, "icon.svg"))
        .thenThrow(new DomainValidationException("name can not be null or blank"));

    assertThatThrownBy(() -> useCase.execute(command))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("name can not be null or blank");

    verify(repository, never()).create(org.mockito.ArgumentMatchers.any());
  }
}