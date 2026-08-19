package dev.tsumakov.infrastructure.profile.contact.usecase;

import dev.tsumakov.application.profile.contact.dto.in.UpdateUserContactDto;
import dev.tsumakov.application.profile.contact.dto.outer.UserContactDto;
import dev.tsumakov.application.profile.contact.port.in.UpdateUserContactUseCase;
import dev.tsumakov.application.profile.contact.usecase.UpdateUserContactUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.support.TransactionTemplate;

@RequiredArgsConstructor
public class TransactionalUpdateUserContactUseCase implements UpdateUserContactUseCase {

  private final UpdateUserContactUseCaseImpl delegate;
  private final TransactionTemplate transactionTemplate;

  @Override
  public UserContactDto execute(UpdateUserContactDto command) {
    return transactionTemplate.execute(status -> delegate.execute(command));
  }
}