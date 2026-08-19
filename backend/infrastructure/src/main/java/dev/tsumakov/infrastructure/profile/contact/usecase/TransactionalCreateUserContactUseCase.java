package dev.tsumakov.infrastructure.profile.contact.usecase;

import dev.tsumakov.application.profile.contact.dto.in.CreateUserContactDto;
import dev.tsumakov.application.profile.contact.dto.outer.UserContactDto;
import dev.tsumakov.application.profile.contact.port.in.CreateUserContactUseCase;
import dev.tsumakov.application.profile.contact.usecase.CreateUserContactUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.support.TransactionTemplate;

@RequiredArgsConstructor
public class TransactionalCreateUserContactUseCase implements CreateUserContactUseCase {

  private final CreateUserContactUseCaseImpl delegate;
  private final TransactionTemplate transactionTemplate;

  @Override
  public UserContactDto execute(CreateUserContactDto command) {
    return transactionTemplate.execute(status -> delegate.execute(command));
  }
}