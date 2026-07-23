package dev.tsumakov.domain.core.repository;

import dev.tsumakov.domain.core.model.Role;
import dev.tsumakov.domain.core.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

  List<User> findAll();

  User save(User user);

  Optional<User> findByEmail(String email);

  Optional<User> findByRole(Role role);
}
