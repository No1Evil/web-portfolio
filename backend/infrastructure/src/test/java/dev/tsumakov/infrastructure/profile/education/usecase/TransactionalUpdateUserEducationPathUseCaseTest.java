package dev.tsumakov.infrastructure.profile.education.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.profile.education.dto.in.UpdateUserEducationPathDto;
import dev.tsumakov.application.profile.education.dto.outer.UserEducationPathDto;
import dev.tsumakov.application.profile.education.usecase.UpdateUserEducationPathUseCaseImpl;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@ExtendWith(MockitoExtension.class)
class TransactionalUpdateUserEducationPathUseCaseTest {

  private static final OffsetDateTime NOW = OffsetDateTime.parse("2026-08-18T10:00:00+02:00");
  private static final UUID ID = UUID.randomUUID();

  @Mock
  private UpdateUserEducationPathUseCaseImpl delegate;
  @Mock
  private TransactionTemplate transactionTemplate;

  private TransactionalUpdateUserEducationPathUseCase useCase;

  @BeforeEach
  void setUp() {
    useCase = new TransactionalUpdateUserEducationPathUseCase(delegate, transactionTemplate);
  }

  @Test
  void shouldExecuteDelegateInsideTransactionAndReturnResult() {
    var command = new UpdateUserEducationPathDto(ID, "MIT", "Boston", Map.of("en", "BSc"),
        NOW, NOW.plusYears(4), true);
    var expected = new UserEducationPathDto(ID, "MIT", "Boston", Map.of("en", "BSc"), NOW,
        NOW.plusYears(4), true, NOW, NOW);

    when(transactionTemplate.execute(any())).thenAnswer(invocation ->
        invocation.<TransactionCallback<UserEducationPathDto>>getArgument(0).doInTransaction(null));
    when(delegate.execute(command)).thenReturn(expected);

    var result = useCase.execute(command);

    assertThat(result).isSameAs(expected);
    verify(delegate).execute(command);
    verify(transactionTemplate).execute(any());
  }
}