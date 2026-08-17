package dev.tsumakov.application.core.skillcategory.usecase;

import dev.tsumakov.application.core.skillcategory.exception.SkillCategoryNotFoundException;
import dev.tsumakov.application.core.skillcategory.mapper.SkillCategoryDtoMapper;
import dev.tsumakov.application.core.skillcategory.port.in.GetSkillCategoryByIdUseCase;
import dev.tsumakov.application.portfolio.dto.api.SkillCategoryDto;
import dev.tsumakov.domain.core.skillcategory.repository.SkillCategoryRepository;

public class GetSkillCategoryByIdUseCaseImpl implements GetSkillCategoryByIdUseCase {

  private final SkillCategoryRepository repository;
  private final SkillCategoryDtoMapper mapper;

  public GetSkillCategoryByIdUseCaseImpl(SkillCategoryRepository repository,
      SkillCategoryDtoMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public SkillCategoryDto execute(Integer categoryId) {
    var category = repository.findById(categoryId).orElseThrow(
        () -> new SkillCategoryNotFoundException(
            "Skill category with id " + categoryId + " not found"));
    return mapper.toDto(category);
  }
}
