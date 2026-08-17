package dev.tsumakov.application.core.user.port.in;

import dev.tsumakov.application.core.user.dto.in.UpdateUserPasswordDto;
import dev.tsumakov.application.core.user.dto.outer.UserDto;

public interface UpdateUserPasswordUseCase {

  UserDto execute(UpdateUserPasswordDto command);

}
