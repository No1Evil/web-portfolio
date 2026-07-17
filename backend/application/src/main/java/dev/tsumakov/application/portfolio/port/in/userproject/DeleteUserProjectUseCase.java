package dev.tsumakov.application.portfolio.port.in.userproject;

import java.util.UUID;

public interface DeleteUserProjectUseCase {
  void execute(Integer projectId, UUID userId);
}
