package dev.tsumakov.infrastructure.profile.skill.usecase;

import dev.tsumakov.application.profile.skill.dto.in.UnassignSkillFromUserDto;
import dev.tsumakov.application.profile.skill.port.in.UnassignSkillFromUserUseCase;
import dev.tsumakov.application.profile.skill.usecase.UnassignSkillFromUserUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.support.TransactionTemplate;

@RequiredArgsConstructor
public class TransactionalUnassignSkillFromUserUseCase implements UnassignSkillFromUserUseCase {

  private final UnassignSkillFromUserUseCaseImpl delegate;
  private final TransactionTemplate transactionTemplate;

  @Override
  public void execute(UnassignSkillFromUserDto command) {
    transactionTemplate.executeWithoutResult(status -> delegate.execute(command));
  }
}