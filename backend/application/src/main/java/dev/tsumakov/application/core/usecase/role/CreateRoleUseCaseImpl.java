package dev.tsumakov.application.core.usecase.role;

import dev.tsumakov.application.core.dto.api.RoleDto;
import dev.tsumakov.application.core.dto.in.CreateRoleDto;
import dev.tsumakov.application.core.mapper.RoleDtoMapper;
import dev.tsumakov.application.core.port.in.role.CreateRoleUseCase;
import dev.tsumakov.domain.core.model.Role;
import dev.tsumakov.domain.core.repository.RoleRepository;

public class CreateRoleUseCaseImpl implements CreateRoleUseCase {

  private final RoleRepository roleRepository;
  private final RoleDtoMapper roleDtoMapper;

  public CreateRoleUseCaseImpl(RoleRepository roleRepository, RoleDtoMapper roleDtoMapper) {
    this.roleRepository = roleRepository;
    this.roleDtoMapper = roleDtoMapper;
  }

  @Override
  public RoleDto execute(CreateRoleDto command) {
    var role = Role.createNew(command.name());
    roleRepository.save(role);
    return roleDtoMapper.toDto(role);
  }
}
