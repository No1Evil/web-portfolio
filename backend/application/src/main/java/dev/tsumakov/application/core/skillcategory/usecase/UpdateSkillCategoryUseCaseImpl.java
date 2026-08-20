package dev.tsumakov.application.core.skillcategory.usecase;

import dev.tsumakov.application.core.skillcategory.dto.in.UpdateSkillCategoryDto;
import dev.tsumakov.application.core.skillcategory.dto.outer.SkillCategoryDto;
import dev.tsumakov.application.core.skillcategory.exception.SkillCategoryNotFoundException;
import dev.tsumakov.application.core.skillcategory.mapper.SkillCategoryDtoMapper;
import dev.tsumakov.application.core.skillcategory.port.in.UpdateSkillCategoryUseCase;
import dev.tsumakov.domain.core.skillcategory.model.SkillCategory;
import dev.tsumakov.domain.core.skillcategory.repository.SkillCategoryRepository;

public class UpdateSkillCategoryUseCaseImpl implements UpdateSkillCategoryUseCase {

  private final SkillCategoryRepository repository;
  private final SkillCategoryDtoMapper mapper;

  public UpdateSkillCategoryUseCaseImpl(SkillCategoryRepository repository,
      SkillCategoryDtoMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public SkillCategoryDto execute(UpdateSkillCategoryDto command) {
    var currentCategory = findSkillCategory(command.skillCategoryId());

    SkillCategory updatedCategory = currentCategory;
    updatedCategory = updateName(updatedCategory, command.name());
    updatedCategory = updateIconUrl(updatedCategory, command.iconUrl());

    if (!currentCategory.equals(updatedCategory)) {
      var savedCategory = repository.update(updatedCategory);
      return mapper.toDto(savedCategory);
    }

    return mapper.toDto(currentCategory);
  }

  private SkillCategory findSkillCategory(Integer skillCategoryId) {
    return repository.findById(skillCategoryId).orElseThrow(
        () -> new SkillCategoryNotFoundException(
            "Skill category with id " + skillCategoryId + " not found"));
  }

  private SkillCategory updateName(SkillCategory category, String name) {
    return name == null ? category : category.updateName(name);
  }

  private SkillCategory updateIconUrl(SkillCategory category, String iconUrl) {
    return iconUrl == null ? category : category.updateIconUrl(iconUrl);
  }
}
