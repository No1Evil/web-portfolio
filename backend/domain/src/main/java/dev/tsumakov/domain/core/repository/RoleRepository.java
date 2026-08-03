package dev.tsumakov.domain.core.repository;

import dev.tsumakov.domain.core.model.Role;
import java.util.List;
import java.util.Optional;

public interface RoleRepository {

  List<Role> findAll();

  Optional<Role> findByName(String name);

  Role save(Role role);

  void delete(Role role);

}
