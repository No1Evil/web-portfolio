package dev.tsumakov.application.portfolio.usecase.skillcategory;

import dev.tsumakov.application.portfolio.dto.api.SkillCategoryDto;
import dev.tsumakov.application.portfolio.mapper.SkillCategoryDtoMapper;
import dev.tsumakov.application.portfolio.port.in.skillcategory.GetAllSkillCategoriesUseCase;
import dev.tsumakov.domain.portfolio.repository.SkillCategoriesRepository;
import java.util.List;

public class GetAllSkillCategoriesUseCaseImpl implements GetAllSkillCategoriesUseCase {

  private final SkillCategoriesRepository skillCategoriesRepository;
  private final SkillCategoryDtoMapper skillCategoryDtoMapper;

  public GetAllSkillCategoriesUseCaseImpl(
      SkillCategoriesRepository skillCategoriesRepository,
      SkillCategoryDtoMapper skillCategoryDtoMapper
  ) {
    this.skillCategoriesRepository = skillCategoriesRepository;
    this.skillCategoryDtoMapper = skillCategoryDtoMapper;
  }

  @Override
  public List<SkillCategoryDto> execute() {
    return skillCategoryDtoMapper.toDtoList(skillCategoriesRepository.findAll());
  }
}
