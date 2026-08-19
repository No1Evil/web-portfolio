package dev.tsumakov.infrastructure.profile.experience.usecase;

import dev.tsumakov.application.profile.experience.dto.in.UpdateUserExperiencePathDto;
import dev.tsumakov.application.profile.experience.dto.outer.UserExperiencePathDto;
import dev.tsumakov.application.profile.experience.port.in.UpdateUserExperiencePathUseCase;
import dev.tsumakov.application.profile.experience.usecase.UpdateUserExperiencePathUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.support.TransactionTemplate;

@RequiredArgsConstructor
public class TransactionalUpdateUserExperiencePathUseCase implements UpdateUserExperiencePathUseCase {

  private final UpdateUserExperiencePathUseCaseImpl delegate;
  private final TransactionTemplate transactionTemplate;

  @Override
  public UserExperiencePathDto execute(UpdateUserExperiencePathDto command) {
    return transactionTemplate.execute(status -> delegate.execute(command));
  }
}