package dev.tsumakov.application.portfolio.port.in.userproject;

import dev.tsumakov.application.portfolio.dto.api.UserProjectDto;
import java.util.List;
import java.util.UUID;

public interface GetUserProjectsByUserIdUseCase {
  List<UserProjectDto> execute(UUID userId);
}
