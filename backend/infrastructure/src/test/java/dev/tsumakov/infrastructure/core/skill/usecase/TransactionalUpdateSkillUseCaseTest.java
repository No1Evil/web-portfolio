package dev.tsumakov.infrastructure.core.skill.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.core.skill.dto.in.UpdateSkillDto;
import dev.tsumakov.application.core.skill.dto.outer.SkillDto;
import dev.tsumakov.application.core.skill.usecase.UpdateSkillUseCaseImpl;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@ExtendWith(MockitoExtension.class)
class TransactionalUpdateSkillUseCaseTest {

  private static final OffsetDateTime NOW = OffsetDateTime.parse("2026-08-18T10:00:00+02:00");

  @Mock
  private UpdateSkillUseCaseImpl delegate;
  @Mock
  private TransactionTemplate transactionTemplate;

  private TransactionalUpdateSkillUseCase useCase;

  @BeforeEach
  void setUp() {
    useCase = new TransactionalUpdateSkillUseCase(delegate, transactionTemplate);
  }

  @Test
  void shouldExecuteDelegateInsideTransactionAndReturnResult() {
    var command = new UpdateSkillDto(5, 2, "Java", "icon.svg");
    var expected = new SkillDto(1, 2, "Java", "icon.svg", NOW, NOW);

    when(transactionTemplate.execute(any())).thenAnswer(invocation ->
        invocation.<TransactionCallback<SkillDto>>getArgument(0).doInTransaction(null));
    when(delegate.execute(command)).thenReturn(expected);

    var result = useCase.execute(command);

    assertThat(result).isSameAs(expected);
    verify(delegate).execute(command);
    verify(transactionTemplate).execute(any());
  }
}