package dev.tsumakov.infrastructure.core.skill.usecase;

import dev.tsumakov.application.core.skill.port.in.DeleteSkillUseCase;
import dev.tsumakov.application.core.skill.usecase.DeleteSkillUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.support.TransactionTemplate;

@RequiredArgsConstructor
public class TransactionalDeleteSkillUseCase implements DeleteSkillUseCase {

  private final DeleteSkillUseCaseImpl delegate;
  private final TransactionTemplate transactionTemplate;

  @Override
  public void execute(Integer skillId) {
    transactionTemplate.executeWithoutResult(status -> delegate.execute(skillId));
  }
}
