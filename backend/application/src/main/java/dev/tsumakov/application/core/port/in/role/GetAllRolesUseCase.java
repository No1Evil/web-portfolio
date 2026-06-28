package dev.tsumakov.application.core.port.in.role;

import dev.tsumakov.application.core.dto.api.RoleDto;
import java.util.List;

public interface GetAllRolesUseCase {
  List<RoleDto> execute();
}
