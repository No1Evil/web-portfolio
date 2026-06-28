package dev.tsumakov.application.portfolio.port.in.userproject;

import dev.tsumakov.application.portfolio.dto.api.UserProjectDto;
import dev.tsumakov.application.portfolio.dto.in.CreateUserProjectDto;

public interface CreateUserProjectUseCase {
  UserProjectDto execute(CreateUserProjectDto command);
}
