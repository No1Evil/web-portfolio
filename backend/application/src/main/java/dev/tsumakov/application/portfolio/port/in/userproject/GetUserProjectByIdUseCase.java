package dev.tsumakov.application.portfolio.port.in.userproject;

import dev.tsumakov.application.portfolio.dto.api.UserProjectDto;
import java.util.Optional;

public interface GetUserProjectByIdUseCase {
  Optional<UserProjectDto> execute(Integer projectId, java.util.UUID userId);
}
