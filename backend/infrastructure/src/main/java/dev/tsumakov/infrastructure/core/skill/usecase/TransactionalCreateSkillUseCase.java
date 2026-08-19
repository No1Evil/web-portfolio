package dev.tsumakov.infrastructure.core.skill.usecase;

import dev.tsumakov.application.core.skill.dto.in.CreateSkillDto;
import dev.tsumakov.application.core.skill.dto.outer.SkillDto;
import dev.tsumakov.application.core.skill.port.in.CreateSkillUseCase;
import dev.tsumakov.application.core.skill.usecase.CreateSkillUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.support.TransactionTemplate;

@RequiredArgsConstructor
public class TransactionalCreateSkillUseCase implements CreateSkillUseCase {

  private final CreateSkillUseCaseImpl delegate;
  private final TransactionTemplate transactionTemplate;

  @Override
  public SkillDto execute(CreateSkillDto command) {
    return transactionTemplate.execute(status -> delegate.execute(command));
  }
}
