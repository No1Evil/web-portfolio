package dev.tsumakov.infrastructure.profile.experience.usecase;

import dev.tsumakov.application.profile.experience.port.in.DeleteUserExperiencePathUseCase;
import dev.tsumakov.application.profile.experience.usecase.DeleteUserExperiencePathUseCaseImpl;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.support.TransactionTemplate;

@RequiredArgsConstructor
public class TransactionalDeleteUserExperiencePathUseCase implements DeleteUserExperiencePathUseCase {

  private final DeleteUserExperiencePathUseCaseImpl delegate;
  private final TransactionTemplate transactionTemplate;

  @Override
  public void execute(UUID userExperiencePathId) {
    transactionTemplate.executeWithoutResult(status -> delegate.execute(userExperiencePathId));
  }
}