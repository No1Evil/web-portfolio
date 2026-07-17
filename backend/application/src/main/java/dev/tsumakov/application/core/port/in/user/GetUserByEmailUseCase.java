package dev.tsumakov.application.core.port.in.user;

import dev.tsumakov.application.core.dto.api.UserDto;
import java.util.Optional;

public interface GetUserByEmailUseCase {
  Optional<UserDto> execute(String email);
}
