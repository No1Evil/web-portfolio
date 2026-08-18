package dev.tsumakov.domain.core.user.repository;

import dev.tsumakov.domain.core.user.model.User;
import dev.tsumakov.domain.shared.repository.CrudRepository;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
  Optional<User> findByUsername(String username);
}
