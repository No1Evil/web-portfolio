package dev.tsumakov.infrastructure.profile.experience.usecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

import dev.tsumakov.application.profile.experience.usecase.DeleteUserExperiencePathUseCaseImpl;
import java.util.UUID;
import java.util.function.Consumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;

@ExtendWith(MockitoExtension.class)
class TransactionalDeleteUserExperiencePathUseCaseTest {

  private static final UUID ID = UUID.randomUUID();

  @Mock
  private DeleteUserExperiencePathUseCaseImpl delegate;
  @Mock
  private TransactionTemplate transactionTemplate;

  private TransactionalDeleteUserExperiencePathUseCase useCase;

  @BeforeEach
  void setUp() {
    useCase = new TransactionalDeleteUserExperiencePathUseCase(delegate, transactionTemplate);
  }

  @Test
  void shouldExecuteDelegateInsideTransactionWithoutResult() {
    doAnswer(invocation -> {
      invocation.<Consumer<TransactionStatus>>getArgument(0).accept(null);
      return null;
    }).when(transactionTemplate).executeWithoutResult(any());

    useCase.execute(ID);

    verify(delegate).execute(ID);
    verify(transactionTemplate).executeWithoutResult(any());
  }
}