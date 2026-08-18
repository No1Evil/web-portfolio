package dev.tsumakov.infrastructure.core.skillcategory.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.core.skillcategory.dto.in.CreateSkillCategoryDto;
import dev.tsumakov.application.core.skillcategory.dto.outer.SkillCategoryDto;
import dev.tsumakov.application.core.skillcategory.usecase.CreateSkillCategoryUseCaseImpl;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@ExtendWith(MockitoExtension.class)
class TransactionalCreateSkillCategoryUseCaseTest {

  private static final OffsetDateTime NOW = OffsetDateTime.parse("2026-08-18T10:00:00+02:00");

  @Mock
  private CreateSkillCategoryUseCaseImpl delegate;
  @Mock
  private TransactionTemplate transactionTemplate;

  private TransactionalCreateSkillCategoryUseCase useCase;

  @BeforeEach
  void setUp() {
    useCase = new TransactionalCreateSkillCategoryUseCase(delegate, transactionTemplate);
  }

  @Test
  void shouldExecuteDelegateInsideTransactionAndReturnResult() {
    var command = new CreateSkillCategoryDto("Backend", "icon.svg");
    var expected = new SkillCategoryDto(1, "Backend", "icon.svg", NOW, NOW);

    when(transactionTemplate.execute(any())).thenAnswer(invocation ->
        invocation.<TransactionCallback<SkillCategoryDto>>getArgument(0).doInTransaction(null));
    when(delegate.execute(command)).thenReturn(expected);

    var result = useCase.execute(command);

    assertThat(result).isSameAs(expected);
    verify(delegate).execute(command);
    verify(transactionTemplate).execute(any());
  }
}