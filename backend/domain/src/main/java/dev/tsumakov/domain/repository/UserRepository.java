package dev.tsumakov.domain.repository;

import dev.tsumakov.domain.model.User;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
  Optional<User> findById(UUID id);

  void save(User user);

  void delete(UUID id);
}
