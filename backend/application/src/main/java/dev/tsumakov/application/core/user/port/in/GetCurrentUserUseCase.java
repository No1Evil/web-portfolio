package dev.tsumakov.application.core.user.port.in;

import dev.tsumakov.application.core.user.dto.outer.UserDto;

public interface GetCurrentUserUseCase {

  UserDto execute(String username);

}