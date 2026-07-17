package dev.tsumakov.application.profile.port.in.userprofile;

import dev.tsumakov.application.profile.dto.api.UserProfileDto;
import dev.tsumakov.application.profile.dto.in.UpdateUserProfileDto;

public interface UpdateUserProfileUseCase {
  UserProfileDto execute(UpdateUserProfileDto command);
}
