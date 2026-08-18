package dev.tsumakov.infrastructure.profile.experience.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.profile.experience.dto.in.UpdateUserExperiencePathDto;
import dev.tsumakov.application.profile.experience.dto.outer.UserExperiencePathDto;
import dev.tsumakov.application.profile.experience.usecase.UpdateUserExperiencePathUseCaseImpl;
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
class TransactionalUpdateUserExperiencePathUseCaseTest {

  private static final OffsetDateTime NOW = OffsetDateTime.parse("2026-08-18T10:00:00+02:00");
  private static final UUID ID = UUID.randomUUID();

  @Mock
  private UpdateUserExperiencePathUseCaseImpl delegate;
  @Mock
  private TransactionTemplate transactionTemplate;

  private TransactionalUpdateUserExperiencePathUseCase useCase;

  @BeforeEach
  void setUp() {
    useCase = new TransactionalUpdateUserExperiencePathUseCase(delegate, transactionTemplate);
  }

  @Test
  void shouldExecuteDelegateInsideTransactionAndReturnResult() {
    var command = new UpdateUserExperiencePathDto(ID, "Google", "Software Engineer",
        "Mountain View", Map.of("en", "Backend"), NOW, NOW.plusYears(2), false);
    var expected = new UserExperiencePathDto(ID, "Google", "Software Engineer", "Mountain View",
        Map.of("en", "Backend"), NOW, NOW.plusYears(2), false, NOW, NOW);

    when(transactionTemplate.execute(any())).thenAnswer(invocation ->
        invocation.<TransactionCallback<UserExperiencePathDto>>getArgument(0).doInTransaction(null));
    when(delegate.execute(command)).thenReturn(expected);

    var result = useCase.execute(command);

    assertThat(result).isSameAs(expected);
    verify(delegate).execute(command);
    verify(transactionTemplate).execute(any());
  }
}