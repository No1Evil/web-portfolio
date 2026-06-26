package dev.tsumakov.domain.repository.core;

import dev.tsumakov.domain.model.core.Role;
import java.util.List;
import java.util.Optional;

public interface RoleRepository {

  List<Role> findAll();

  Optional<Role> findByName(String name);

  void save(Role role);

  void delete(Role role);

}
