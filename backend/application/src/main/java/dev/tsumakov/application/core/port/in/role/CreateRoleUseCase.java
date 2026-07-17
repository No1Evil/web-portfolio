package dev.tsumakov.application.core.port.in.role;

import dev.tsumakov.application.core.dto.api.RoleDto;
import dev.tsumakov.application.core.dto.in.CreateRoleDto;

public interface CreateRoleUseCase {
  RoleDto execute(CreateRoleDto command);
}
