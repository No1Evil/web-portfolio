package dev.tsumakov.application.core.user.port.in;

import dev.tsumakov.application.core.user.dto.in.AuthenticateUserDto;
import dev.tsumakov.application.core.user.dto.outer.UserDto;

public interface AuthenticateUserUseCase {

  UserDto execute(AuthenticateUserDto command);

}
