package dev.tsumakov.infrastructure.core.persistence.repository;

import dev.tsumakov.infrastructure.core.persistence.entity.UserEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSpringDataRepository extends JpaRepository<UserEntity, UUID> {

  Optional<UserEntity> findByEmail(String email);
}
