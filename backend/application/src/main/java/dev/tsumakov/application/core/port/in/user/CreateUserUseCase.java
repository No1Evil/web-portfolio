package dev.tsumakov.application.core.port.in.user;

import dev.tsumakov.application.core.dto.api.UserDto;
import dev.tsumakov.application.core.dto.in.CreateUserDto;

public interface CreateUserUseCase {
  UserDto execute(CreateUserDto command);
}
