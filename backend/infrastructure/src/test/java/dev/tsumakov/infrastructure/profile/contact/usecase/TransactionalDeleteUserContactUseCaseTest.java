package dev.tsumakov.infrastructure.profile.contact.usecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

import dev.tsumakov.application.profile.contact.usecase.DeleteUserContactUseCaseImpl;
import java.util.function.Consumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;

@ExtendWith(MockitoExtension.class)
class TransactionalDeleteUserContactUseCaseTest {

  @Mock
  private DeleteUserContactUseCaseImpl delegate;
  @Mock
  private TransactionTemplate transactionTemplate;

  private TransactionalDeleteUserContactUseCase useCase;

  @BeforeEach
  void setUp() {
    useCase = new TransactionalDeleteUserContactUseCase(delegate, transactionTemplate);
  }

  @Test
  void shouldExecuteDelegateInsideTransactionWithoutResult() {
    doAnswer(invocation -> {
      invocation.<Consumer<TransactionStatus>>getArgument(0).accept(null);
      return null;
    }).when(transactionTemplate).executeWithoutResult(any());

    useCase.execute(1);

    verify(delegate).execute(1);
    verify(transactionTemplate).executeWithoutResult(any());
  }
}