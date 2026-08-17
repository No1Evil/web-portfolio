package dev.tsumakov.application.core.skillcategory.usecase;

import dev.tsumakov.application.core.skillcategory.port.in.DeleteSkillCategoryUseCase;
import dev.tsumakov.domain.core.skillcategory.repository.SkillCategoryRepository;

public class DeleteSkillCategoryUseCaseImpl implements DeleteSkillCategoryUseCase {

  private final SkillCategoryRepository repository;

  public DeleteSkillCategoryUseCaseImpl(SkillCategoryRepository repository) {
    this.repository = repository;
  }

  @Override
  public void execute(Integer skillCategoryId) {
    repository.delete(skillCategoryId);
  }
}
