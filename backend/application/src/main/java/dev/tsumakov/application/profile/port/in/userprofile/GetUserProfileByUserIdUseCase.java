package dev.tsumakov.application.profile.port.in.userprofile;

import dev.tsumakov.application.profile.dto.api.UserProfileDto;
import java.util.Optional;
import java.util.UUID;

public interface GetUserProfileByUserIdUseCase {
  Optional<UserProfileDto> execute(UUID userId);
}
