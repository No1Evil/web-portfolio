package dev.tsumakov.infrastructure.profile.summary.usecase;

import dev.tsumakov.application.profile.summary.dto.in.CreateUserSummaryDto;
import dev.tsumakov.application.profile.summary.dto.outer.UserSummaryDto;
import dev.tsumakov.application.profile.summary.port.in.CreateUserSummaryUseCase;
import dev.tsumakov.application.profile.summary.usecase.CreateUserSummaryUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.support.TransactionTemplate;

@RequiredArgsConstructor
public class TransactionalCreateUserSummaryUseCase implements CreateUserSummaryUseCase {

  private final CreateUserSummaryUseCaseImpl delegate;
  private final TransactionTemplate transactionTemplate;

  @Override
  public UserSummaryDto execute(CreateUserSummaryDto command) {
    return transactionTemplate.execute(status -> delegate.execute(command));
  }
}