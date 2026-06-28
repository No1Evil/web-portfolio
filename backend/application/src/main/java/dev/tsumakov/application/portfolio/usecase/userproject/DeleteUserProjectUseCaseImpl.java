package dev.tsumakov.application.portfolio.usecase.userproject;

import dev.tsumakov.application.portfolio.port.in.userproject.DeleteUserProjectUseCase;
import dev.tsumakov.domain.portfolio.repository.UserProjectRepository;
import java.util.UUID;

public class DeleteUserProjectUseCaseImpl implements DeleteUserProjectUseCase {

  private final UserProjectRepository userProjectRepository;

  public DeleteUserProjectUseCaseImpl(UserProjectRepository userProjectRepository) {
    this.userProjectRepository = userProjectRepository;
  }

  @Override
  public void execute(Integer projectId, UUID userId) {
    userProjectRepository.deleteByIdAndUserId(projectId, userId);
  }
}
