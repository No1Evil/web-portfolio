package dev.tsumakov.application.core.skillcategory.usecase;

import dev.tsumakov.application.core.skillcategory.mapper.SkillCategoryDtoMapper;
import dev.tsumakov.application.core.skillcategory.port.in.GetAllSkillCategoriesUseCase;
import dev.tsumakov.application.portfolio.dto.api.SkillCategoryDto;
import dev.tsumakov.domain.core.skillcategory.repository.SkillCategoryRepository;
import java.util.List;

public class GetAllSkillCategoriesUseCaseImpl implements GetAllSkillCategoriesUseCase {

  private final SkillCategoryRepository repository;
  private final SkillCategoryDtoMapper mapper;

  public GetAllSkillCategoriesUseCaseImpl(SkillCategoryRepository repository,
      SkillCategoryDtoMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public List<SkillCategoryDto> execute() {
    return repository.findAll().stream().map(mapper::toDto).toList();
  }
}
