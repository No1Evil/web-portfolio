package dev.tsumakov.infrastructure.core.user.persistence.repository;

import dev.tsumakov.infrastructure.core.user.persistence.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSpringDataRepository extends JpaRepository<UserEntity, Integer> {

  Optional<UserEntity> findByUsername(String username);
}
