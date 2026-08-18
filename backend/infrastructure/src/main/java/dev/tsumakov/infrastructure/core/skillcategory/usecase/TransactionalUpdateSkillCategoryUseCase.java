package dev.tsumakov.infrastructure.core.skillcategory.usecase;

import dev.tsumakov.application.core.skillcategory.dto.in.UpdateSkillCategoryDto;
import dev.tsumakov.application.core.skillcategory.dto.outer.SkillCategoryDto;
import dev.tsumakov.application.core.skillcategory.port.in.UpdateSkillCategoryUseCase;
import dev.tsumakov.application.core.skillcategory.usecase.UpdateSkillCategoryUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.support.TransactionTemplate;

@RequiredArgsConstructor
public class TransactionalUpdateSkillCategoryUseCase implements UpdateSkillCategoryUseCase {

  private final UpdateSkillCategoryUseCaseImpl delegate;
  private final TransactionTemplate transactionTemplate;

  @Override
  public SkillCategoryDto execute(UpdateSkillCategoryDto command) {
    return transactionTemplate.execute(status -> delegate.execute(command));
  }
}