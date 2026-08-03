package dev.tsumakov.application.portfolio.usecase.skillcategory;

import dev.tsumakov.application.portfolio.dto.api.SkillCategoryDto;
import dev.tsumakov.application.portfolio.dto.in.CreateSkillCategoryDto;
import dev.tsumakov.application.portfolio.mapper.SkillCategoryDtoMapper;
import dev.tsumakov.application.portfolio.port.in.skillcategory.CreateSkillCategoryUseCase;
import dev.tsumakov.domain.portfolio.model.SkillCategory;
import dev.tsumakov.domain.portfolio.repository.SkillCategoriesRepository;

public class CreateSkillCategoryUseCaseImpl implements CreateSkillCategoryUseCase {

  private final SkillCategoriesRepository skillCategoriesRepository;
  private final SkillCategoryDtoMapper skillCategoryDtoMapper;

  public CreateSkillCategoryUseCaseImpl(
      SkillCategoriesRepository skillCategoriesRepository,
      SkillCategoryDtoMapper skillCategoryDtoMapper
  ) {
    this.skillCategoriesRepository = skillCategoriesRepository;
    this.skillCategoryDtoMapper = skillCategoryDtoMapper;
  }

  @Override
  public SkillCategoryDto execute(CreateSkillCategoryDto command) {
    var category = SkillCategory.createNew(command.name(), command.iconUrl());
    skillCategoriesRepository.save(category);
    return skillCategoryDtoMapper.toDto(category);
  }
}
