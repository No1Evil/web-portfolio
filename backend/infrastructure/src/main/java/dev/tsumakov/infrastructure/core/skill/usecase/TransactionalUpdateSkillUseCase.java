package dev.tsumakov.infrastructure.core.skill.usecase;

import dev.tsumakov.application.core.skill.dto.in.UpdateSkillDto;
import dev.tsumakov.application.core.skill.dto.outer.SkillDto;
import dev.tsumakov.application.core.skill.port.in.UpdateSkillUseCase;
import dev.tsumakov.application.core.skill.usecase.UpdateSkillUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.support.TransactionTemplate;

@RequiredArgsConstructor
public class TransactionalUpdateSkillUseCase implements UpdateSkillUseCase {

  private final UpdateSkillUseCaseImpl delegate;
  private final TransactionTemplate transactionTemplate;

  @Override
  public SkillDto execute(UpdateSkillDto command) {
    return transactionTemplate.execute(status -> delegate.execute(command));
  }
}
