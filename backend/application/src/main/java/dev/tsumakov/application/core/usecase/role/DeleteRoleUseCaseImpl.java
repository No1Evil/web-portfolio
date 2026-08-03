package dev.tsumakov.application.core.usecase.role;

import dev.tsumakov.application.core.port.in.role.DeleteRoleUseCase;
import dev.tsumakov.application.shared.exception.ApplicationException;
import dev.tsumakov.domain.core.model.Role;
import dev.tsumakov.domain.core.repository.RoleRepository;
import dev.tsumakov.domain.shared.util.DomainObjects;

public class DeleteRoleUseCaseImpl implements DeleteRoleUseCase {

  private final RoleRepository roleRepository;

  public DeleteRoleUseCaseImpl(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  @Override
  public void execute(String name) {
    DomainObjects.requireNonNull(name);

    String normalizedName = name.trim().toUpperCase();

    if (normalizedName.equals(Role.USER_ROLE_NAME))
      throw new ApplicationException("Can not delete default user role");

    if (normalizedName.equals(Role.ADMIN_ROLE_NAME))
      throw new ApplicationException("Can not delete default admin role");

    roleRepository.findByName(name).ifPresent(roleRepository::delete);
  }
}
