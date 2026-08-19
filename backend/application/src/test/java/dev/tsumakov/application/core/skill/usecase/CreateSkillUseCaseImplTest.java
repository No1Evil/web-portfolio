package dev.tsumakov.application.core.skill.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.core.skill.dto.in.CreateSkillDto;
import dev.tsumakov.application.core.skill.dto.outer.SkillDto;
import dev.tsumakov.application.core.skill.mapper.SkillDtoMapper;
import dev.tsumakov.domain.core.skill.factory.SkillFactory;
import dev.tsumakov.domain.core.skill.model.Skill;
import dev.tsumakov.domain.core.skill.repository.SkillRepository;
import dev.tsumakov.domain.core.skillcategory.model.SkillCategory;
import dev.tsumakov.domain.core.skillcategory.repository.SkillCategoryRepository;
import dev.tsumakov.domain.shared.exception.DomainValidationException;
import java.time.OffsetDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateSkillUseCaseImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.now();

  @Mock
  private SkillFactory factory;
  @Mock
  private SkillRepository repository;
  @Mock
  private SkillCategoryRepository skillCategoryRepository;
  @Mock
  private SkillDtoMapper mapper;

  private CreateSkillUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new CreateSkillUseCaseImpl(factory, repository, skillCategoryRepository, mapper);
  }

  @Test
  void shouldCreateSkillAndReturnDto() {
    var command = new CreateSkillDto(2, "Java", "icon.svg");
    var freshSkill = new Skill(null, 2, "Java", "icon.svg", NOW, NOW, 1L);
    var savedSkill = new Skill(1, 2, "Java", "icon.svg", NOW, NOW, 1L);
    var expectedDto = new SkillDto(1, 2, "Java", "icon.svg", NOW, NOW);
    var skillCategory = new SkillCategory(2, "name", "url", 1L, NOW, NOW);

    when(factory.createNew(command.categoryId(), command.name(), command.iconUrl()))
        .thenReturn(freshSkill);
    when(repository.create(freshSkill)).thenReturn(savedSkill);
    when(mapper.toDto(savedSkill)).thenReturn(expectedDto);
    when(skillCategoryRepository.findById(command.categoryId())).thenReturn(
        Optional.of(skillCategory));
    var result = useCase.execute(command);

    assertThat(result).isEqualTo(expectedDto);
    verify(factory).createNew(2, "Java", "icon.svg");
    verify(repository).create(freshSkill);
    verify(mapper).toDto(savedSkill);
  }

  @Test
  void shouldPropagateDomainValidationExceptionWhenNameIsBlank() {
    var command = new CreateSkillDto(2, "  ", "icon.svg");
    var skillCategory = new SkillCategory(2, "name", "url", 1L, NOW, NOW);

    when(factory.createNew(2, "  ", "icon.svg"))
        .thenThrow(new DomainValidationException("name can not be null or blank"));

    when(skillCategoryRepository.findById(command.categoryId())).thenReturn(
        Optional.of(skillCategory));

    assertThatThrownBy(() -> useCase.execute(command))
        .isInstanceOf(DomainValidationException.class);

    verify(repository, never()).create(org.mockito.ArgumentMatchers.any());
  }
}