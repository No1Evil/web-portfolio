package dev.tsumakov.infrastructure.profile.summary.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.profile.summary.dto.in.UpdateUserSummaryDto;
import dev.tsumakov.application.profile.summary.dto.outer.UserSummaryDto;
import dev.tsumakov.application.profile.summary.usecase.UpdateUserSummaryUseCaseImpl;
import java.time.OffsetDateTime;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@ExtendWith(MockitoExtension.class)
class TransactionalUpdateUserSummaryUseCaseTest {

  private static final OffsetDateTime NOW = OffsetDateTime.parse("2026-08-18T10:00:00+02:00");

  @Mock
  private UpdateUserSummaryUseCaseImpl delegate;
  @Mock
  private TransactionTemplate transactionTemplate;

  private TransactionalUpdateUserSummaryUseCase useCase;

  @BeforeEach
  void setUp() {
    useCase = new TransactionalUpdateUserSummaryUseCase(delegate, transactionTemplate);
  }

  @Test
  void shouldExecuteDelegateInsideTransactionAndReturnResult() {
    var command = new UpdateUserSummaryDto("John", "Doe", "Senior", Map.of("en", "desc"),
        "hero.svg", NOW, NOW);
    var expected = new UserSummaryDto(1, "John", "Doe", "Senior", Map.of("en", "desc"),
        "hero.svg", NOW, NOW);

    when(transactionTemplate.execute(any())).thenAnswer(invocation ->
        invocation.<TransactionCallback<UserSummaryDto>>getArgument(0).doInTransaction(null));
    when(delegate.execute(command)).thenReturn(expected);

    var result = useCase.execute(command);

    assertThat(result).isSameAs(expected);
    verify(delegate).execute(command);
    verify(transactionTemplate).execute(any());
  }
}