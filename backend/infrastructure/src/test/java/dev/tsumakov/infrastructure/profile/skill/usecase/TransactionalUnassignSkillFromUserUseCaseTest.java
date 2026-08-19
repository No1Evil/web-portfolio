package dev.tsumakov.infrastructure.profile.skill.usecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

import dev.tsumakov.application.profile.skill.dto.in.UnassignSkillFromUserDto;
import dev.tsumakov.application.profile.skill.usecase.UnassignSkillFromUserUseCaseImpl;
import java.util.function.Consumer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;

@ExtendWith(MockitoExtension.class)
class TransactionalUnassignSkillFromUserUseCaseTest {

  @Mock
  private UnassignSkillFromUserUseCaseImpl delegate;
  @Mock
  private TransactionTemplate transactionTemplate;

  private TransactionalUnassignSkillFromUserUseCase useCase;

  @BeforeEach
  void setUp() {
    useCase = new TransactionalUnassignSkillFromUserUseCase(delegate, transactionTemplate);
  }

  @Test
  void shouldExecuteDelegateInsideTransactionWithoutResult() {
    var command = new UnassignSkillFromUserDto(1, 3);

    doAnswer(invocation -> {
      invocation.<Consumer<TransactionStatus>>getArgument(0).accept(null);
      return null;
    }).when(transactionTemplate).executeWithoutResult(any());

    useCase.execute(command);

    verify(delegate).execute(command);
    verify(transactionTemplate).executeWithoutResult(any());
  }
}