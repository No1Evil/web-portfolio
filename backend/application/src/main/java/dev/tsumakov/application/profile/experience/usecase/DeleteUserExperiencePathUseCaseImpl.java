package dev.tsumakov.application.profile.experience.usecase;

import dev.tsumakov.application.profile.experience.port.in.DeleteUserExperiencePathUseCase;
import dev.tsumakov.domain.profile.experience.repository.UserExperiencePathRepository;
import java.util.UUID;

public class DeleteUserExperiencePathUseCaseImpl implements DeleteUserExperiencePathUseCase {

  private final UserExperiencePathRepository repository;

  public DeleteUserExperiencePathUseCaseImpl(UserExperiencePathRepository repository) {
    this.repository = repository;
  }

  @Override
  public void execute(UUID userExperiencePathId) {
    repository.delete(userExperiencePathId);
  }
}
