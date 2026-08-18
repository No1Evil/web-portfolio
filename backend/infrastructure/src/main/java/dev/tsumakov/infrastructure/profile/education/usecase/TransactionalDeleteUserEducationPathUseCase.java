package dev.tsumakov.infrastructure.profile.education.usecase;

import dev.tsumakov.application.profile.education.port.in.DeleteUserEducationPathUseCase;
import dev.tsumakov.application.profile.education.usecase.DeleteUserEducationPathUseCaseImpl;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.support.TransactionTemplate;

@RequiredArgsConstructor
public class TransactionalDeleteUserEducationPathUseCase implements DeleteUserEducationPathUseCase {

  private final DeleteUserEducationPathUseCaseImpl delegate;
  private final TransactionTemplate transactionTemplate;

  @Override
  public void execute(UUID userEducationPathId) {
    transactionTemplate.executeWithoutResult(status -> delegate.execute(userEducationPathId));
  }
}