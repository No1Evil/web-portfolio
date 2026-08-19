package dev.tsumakov.infrastructure.core.skillcategory.usecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

import dev.tsumakov.application.core.skillcategory.usecase.DeleteSkillCategoryUseCaseImpl;
import java.util.function.Consumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;

@ExtendWith(MockitoExtension.class)
class TransactionalDeleteSkillCategoryUseCaseTest {

  @Mock
  private DeleteSkillCategoryUseCaseImpl delegate;
  @Mock
  private TransactionTemplate transactionTemplate;

  private TransactionalDeleteSkillCategoryUseCase useCase;

  @BeforeEach
  void setUp() {
    useCase = new TransactionalDeleteSkillCategoryUseCase(delegate, transactionTemplate);
  }

  @Test
  void shouldExecuteDelegateInsideTransactionWithoutResult() {
    doAnswer(invocation -> {
      invocation.<Consumer<TransactionStatus>>getArgument(0).accept(null);
      return null;
    }).when(transactionTemplate).executeWithoutResult(any());

    useCase.execute(5);

    verify(delegate).execute(5);
    verify(transactionTemplate).executeWithoutResult(any());
  }
}