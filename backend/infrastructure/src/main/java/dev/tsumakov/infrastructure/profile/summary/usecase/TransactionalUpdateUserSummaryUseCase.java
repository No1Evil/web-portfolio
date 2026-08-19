package dev.tsumakov.infrastructure.profile.summary.usecase;

import dev.tsumakov.application.profile.summary.dto.in.UpdateUserSummaryDto;
import dev.tsumakov.application.profile.summary.dto.outer.UserSummaryDto;
import dev.tsumakov.application.profile.summary.port.in.UpdateUserSummaryUseCase;
import dev.tsumakov.application.profile.summary.usecase.UpdateUserSummaryUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.support.TransactionTemplate;

@RequiredArgsConstructor
public class TransactionalUpdateUserSummaryUseCase implements UpdateUserSummaryUseCase {

  private final UpdateUserSummaryUseCaseImpl delegate;
  private final TransactionTemplate transactionTemplate;

  @Override
  public UserSummaryDto execute(UpdateUserSummaryDto command) {
    return transactionTemplate.execute(status -> delegate.execute(command));
  }
}