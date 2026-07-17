package dev.tsumakov.application.portfolio.usecase.skillcategory;

import dev.tsumakov.application.portfolio.port.in.skillcategory.DeleteSkillCategoryUseCase;
import dev.tsumakov.domain.portfolio.repository.SkillCategoriesRepository;

public class DeleteSkillCategoryUseCaseImpl implements DeleteSkillCategoryUseCase {

  private final SkillCategoriesRepository skillCategoriesRepository;

  public DeleteSkillCategoryUseCaseImpl(SkillCategoriesRepository skillCategoriesRepository) {
    this.skillCategoriesRepository = skillCategoriesRepository;
  }

  @Override
  public void execute(Integer id) {
    skillCategoriesRepository.deleteById(id);
  }
}
