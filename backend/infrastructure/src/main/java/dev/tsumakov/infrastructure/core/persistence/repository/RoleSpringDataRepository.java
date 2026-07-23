package dev.tsumakov.infrastructure.core.persistence.repository;

import dev.tsumakov.infrastructure.core.persistence.entity.RoleEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleSpringDataRepository extends JpaRepository<RoleEntity, Integer> {

  Optional<RoleEntity> findByName(String name);
}
