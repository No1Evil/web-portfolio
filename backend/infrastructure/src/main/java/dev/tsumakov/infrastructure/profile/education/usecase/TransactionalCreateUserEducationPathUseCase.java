package dev.tsumakov.infrastructure.profile.education.usecase;

import dev.tsumakov.application.profile.education.dto.in.CreateUserEducationPathDto;
import dev.tsumakov.application.profile.education.dto.outer.UserEducationPathDto;
import dev.tsumakov.application.profile.education.port.in.CreateUserEducationPathUseCase;
import dev.tsumakov.application.profile.education.usecase.CreateUserEducationPathUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.support.TransactionTemplate;

@RequiredArgsConstructor
public class TransactionalCreateUserEducationPathUseCase implements CreateUserEducationPathUseCase {

  private final CreateUserEducationPathUseCaseImpl delegate;
  private final TransactionTemplate transactionTemplate;

  @Override
  public UserEducationPathDto execute(CreateUserEducationPathDto command) {
    return transactionTemplate.execute(status -> delegate.execute(command));
  }
}