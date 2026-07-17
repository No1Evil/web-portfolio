package dev.tsumakov.application.portfolio.usecase.skillcategory;

import dev.tsumakov.application.portfolio.dto.api.SkillCategoryDto;
import dev.tsumakov.application.portfolio.mapper.SkillCategoryDtoMapper;
import dev.tsumakov.application.portfolio.port.in.skillcategory.GetSkillCategoryByIdUseCase;
import dev.tsumakov.domain.portfolio.repository.SkillCategoriesRepository;
import java.util.Optional;

public class GetSkillCategoryByIdUseCaseImpl implements GetSkillCategoryByIdUseCase {

  private final SkillCategoriesRepository skillCategoriesRepository;
  private final SkillCategoryDtoMapper skillCategoryDtoMapper;

  public GetSkillCategoryByIdUseCaseImpl(
      SkillCategoriesRepository skillCategoriesRepository,
      SkillCategoryDtoMapper skillCategoryDtoMapper
  ) {
    this.skillCategoriesRepository = skillCategoriesRepository;
    this.skillCategoryDtoMapper = skillCategoryDtoMapper;
  }

  @Override
  public Optional<SkillCategoryDto> execute(Integer id) {
    return skillCategoriesRepository.findById(id).map(skillCategoryDtoMapper::toDto);
  }
}
