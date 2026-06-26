package dev.tsumakov.domain.repository.core;

import dev.tsumakov.domain.model.core.Role;
import dev.tsumakov.domain.model.core.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

  List<User> findAll();

  void save(User user);

  Optional<User> findByEmail(String email);

  Optional<User> findByRole(Role role);
}
