package dev.tsumakov.infrastructure.core.user.usecase;

import dev.tsumakov.application.core.user.dto.in.UpdateUserPasswordDto;
import dev.tsumakov.application.core.user.dto.outer.UserDto;
import dev.tsumakov.application.core.user.port.in.UpdateUserPasswordUseCase;
import dev.tsumakov.application.core.user.usecase.UpdateUserPasswordUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.support.TransactionTemplate;

@RequiredArgsConstructor
public class TransactionalUpdateUserPasswordUseCase implements UpdateUserPasswordUseCase {

  private final UpdateUserPasswordUseCaseImpl delegate;
  private final TransactionTemplate transactionTemplate;

  @Override
  public UserDto execute(UpdateUserPasswordDto command) {
    return transactionTemplate.execute(status -> delegate.execute(command));
  }
}