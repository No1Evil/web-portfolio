package dev.tsumakov.infrastructure.profile.education.usecase;

import dev.tsumakov.application.profile.education.dto.in.UpdateUserEducationPathDto;
import dev.tsumakov.application.profile.education.dto.outer.UserEducationPathDto;
import dev.tsumakov.application.profile.education.port.in.UpdateUserEducationPathUseCase;
import dev.tsumakov.application.profile.education.usecase.UpdateUserEducationPathUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.support.TransactionTemplate;

@RequiredArgsConstructor
public class TransactionalUpdateUserEducationPathUseCase implements UpdateUserEducationPathUseCase {

  private final UpdateUserEducationPathUseCaseImpl delegate;
  private final TransactionTemplate transactionTemplate;

  @Override
  public UserEducationPathDto execute(UpdateUserEducationPathDto command) {
    return transactionTemplate.execute(status -> delegate.execute(command));
  }
}