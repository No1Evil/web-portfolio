package dev.tsumakov.infrastructure.core.user.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.core.user.dto.in.UpdateUserPasswordDto;
import dev.tsumakov.application.core.user.dto.outer.UserDto;
import dev.tsumakov.application.core.user.usecase.UpdateUserPasswordUseCaseImpl;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@ExtendWith(MockitoExtension.class)
class TransactionalUpdateUserPasswordUseCaseTest {

  private static final OffsetDateTime NOW = OffsetDateTime.parse("2026-08-18T10:00:00+02:00");

  @Mock
  private UpdateUserPasswordUseCaseImpl delegate;
  @Mock
  private TransactionTemplate transactionTemplate;

  private TransactionalUpdateUserPasswordUseCase useCase;

  @BeforeEach
  void setUp() {
    useCase = new TransactionalUpdateUserPasswordUseCase(delegate, transactionTemplate);
  }

  @Test
  void shouldExecuteDelegateInsideTransactionAndReturnResult() {
    var command = new UpdateUserPasswordDto(1, "old", "new");
    var expected = new UserDto(1, "john", NOW, NOW);

    when(transactionTemplate.execute(any())).thenAnswer(invocation ->
        invocation.<TransactionCallback<UserDto>>getArgument(0).doInTransaction(null));
    when(delegate.execute(command)).thenReturn(expected);

    var result = useCase.execute(command);

    assertThat(result).isSameAs(expected);
    verify(delegate).execute(command);
    verify(transactionTemplate).execute(any());
  }
}