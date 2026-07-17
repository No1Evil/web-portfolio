package dev.tsumakov.application.core.usecase.role;

import dev.tsumakov.application.core.port.in.role.DeleteRoleUseCase;
import dev.tsumakov.domain.core.repository.RoleRepository;

public class DeleteRoleUseCaseImpl implements DeleteRoleUseCase {

  private final RoleRepository roleRepository;

  public DeleteRoleUseCaseImpl(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Override
  public void execute(String name) {
    roleRepository.findByName(name).ifPresent(roleRepository::delete);
  }
}
