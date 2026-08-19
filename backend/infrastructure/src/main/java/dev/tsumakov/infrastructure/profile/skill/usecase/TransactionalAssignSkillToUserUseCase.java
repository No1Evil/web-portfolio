package dev.tsumakov.infrastructure.profile.skill.usecase;

import dev.tsumakov.application.profile.skill.dto.in.AssignSkillToUserDto;
import dev.tsumakov.application.profile.skill.port.in.AssignSkillToUserUseCase;
import dev.tsumakov.application.profile.skill.usecase.AssignSkillToUserUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.support.TransactionTemplate;

@RequiredArgsConstructor
public class TransactionalAssignSkillToUserUseCase implements AssignSkillToUserUseCase {

  private final AssignSkillToUserUseCaseImpl delegate;
  private final TransactionTemplate transactionTemplate;

  @Override
  public void execute(AssignSkillToUserDto command) {
    transactionTemplate.executeWithoutResult(status -> delegate.execute(command));
  }
}