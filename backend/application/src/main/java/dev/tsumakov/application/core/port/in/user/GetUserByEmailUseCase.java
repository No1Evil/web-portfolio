package dev.tsumakov.application.core.port.in.user;

import dev.tsumakov.application.core.dto.api.UserDto;

public interface GetUserByEmailUseCase {
  UserDto execute(String email);
}
