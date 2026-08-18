package dev.tsumakov.infrastructure.profile.contact.usecase;

import dev.tsumakov.application.profile.contact.port.in.DeleteUserContactUseCase;
import dev.tsumakov.application.profile.contact.usecase.DeleteUserContactUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.support.TransactionTemplate;

@RequiredArgsConstructor
public class TransactionalDeleteUserContactUseCase implements DeleteUserContactUseCase {

  private final DeleteUserContactUseCaseImpl delegate;
  private final TransactionTemplate transactionTemplate;

  @Override
  public void execute(Integer userContactId) {
    transactionTemplate.executeWithoutResult(status -> delegate.execute(userContactId));
  }
}