package dev.tsumakov.application.profile.education.usecase;

import dev.tsumakov.application.profile.education.port.in.DeleteUserEducationPathUseCase;
import dev.tsumakov.domain.profile.education.repository.UserEducationPathRepository;
import java.util.UUID;

public class DeleteUserEducationPathUseCaseImpl implements DeleteUserEducationPathUseCase {

  private final UserEducationPathRepository repository;

  public DeleteUserEducationPathUseCaseImpl(UserEducationPathRepository repository) {
    this.repository = repository;
  }

  @Override
  public void execute(UUID userEducationPathId) {
    repository.delete(userEducationPathId);
  }
}
