package dev.tsumakov.application.core.usecase.role;

import dev.tsumakov.application.core.dto.api.RoleDto;
import dev.tsumakov.application.core.mapper.RoleDtoMapper;
import dev.tsumakov.application.core.port.in.role.GetAllRolesUseCase;
import dev.tsumakov.domain.core.repository.RoleRepository;
import java.util.List;

public class GetAllRolesUseCaseImpl implements GetAllRolesUseCase {

  private final RoleRepository roleRepository;
  private final RoleDtoMapper roleDtoMapper;

  public GetAllRolesUseCaseImpl(RoleRepository roleRepository, RoleDtoMapper roleDtoMapper) {
    this.roleRepository = roleRepository;
    this.roleDtoMapper = roleDtoMapper;
  }

  @Override
  public List<RoleDto> execute() {
    return roleDtoMapper.toDtoList(roleRepository.findAll());
  }
}
