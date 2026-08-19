package dev.tsumakov.infrastructure.core.skillcategory.usecase;

import dev.tsumakov.application.core.skillcategory.dto.in.CreateSkillCategoryDto;
import dev.tsumakov.application.core.skillcategory.dto.outer.SkillCategoryDto;
import dev.tsumakov.application.core.skillcategory.port.in.CreateSkillCategoryUseCase;
import dev.tsumakov.application.core.skillcategory.usecase.CreateSkillCategoryUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.support.TransactionTemplate;

@RequiredArgsConstructor
public class TransactionalCreateSkillCategoryUseCase implements CreateSkillCategoryUseCase {

  private final CreateSkillCategoryUseCaseImpl delegate;
  private final TransactionTemplate transactionTemplate;

  @Override
  public SkillCategoryDto execute(CreateSkillCategoryDto command) {
    return transactionTemplate.execute(status -> delegate.execute(command));
  }
}