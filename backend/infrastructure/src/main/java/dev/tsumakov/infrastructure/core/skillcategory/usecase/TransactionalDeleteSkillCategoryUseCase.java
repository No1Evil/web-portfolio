package dev.tsumakov.infrastructure.core.skillcategory.usecase;

import dev.tsumakov.application.core.skillcategory.port.in.DeleteSkillCategoryUseCase;
import dev.tsumakov.application.core.skillcategory.usecase.DeleteSkillCategoryUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.support.TransactionTemplate;

@RequiredArgsConstructor
public class TransactionalDeleteSkillCategoryUseCase implements DeleteSkillCategoryUseCase {

  private final DeleteSkillCategoryUseCaseImpl delegate;
  private final TransactionTemplate transactionTemplate;

  @Override
  public void execute(Integer skillCategoryId) {
    transactionTemplate.executeWithoutResult(status -> delegate.execute(skillCategoryId));
  }
}