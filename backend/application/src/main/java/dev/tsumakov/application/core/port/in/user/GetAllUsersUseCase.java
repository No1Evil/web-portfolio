package dev.tsumakov.application.core.port.in.user;

import dev.tsumakov.application.core.dto.api.UserDto;
import java.util.List;

public interface GetAllUsersUseCase {
  List<UserDto> execute();
}
