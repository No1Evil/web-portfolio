package dev.tsumakov.infrastructure.profile.experience.usecase;

import dev.tsumakov.application.profile.experience.dto.in.CreateUserExperiencePathDto;
import dev.tsumakov.application.profile.experience.dto.outer.UserExperiencePathDto;
import dev.tsumakov.application.profile.experience.port.in.CreateUserExperiencePathUseCase;
import dev.tsumakov.application.profile.experience.usecase.CreateUserExperiencePathUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.support.TransactionTemplate;

@RequiredArgsConstructor
public class TransactionalCreateUserExperiencePathUseCase implements CreateUserExperiencePathUseCase {

  private final CreateUserExperiencePathUseCaseImpl delegate;
  private final TransactionTemplate transactionTemplate;

  @Override
  public UserExperiencePathDto execute(CreateUserExperiencePathDto command) {
    return transactionTemplate.execute(status -> delegate.execute(command));
  }
}