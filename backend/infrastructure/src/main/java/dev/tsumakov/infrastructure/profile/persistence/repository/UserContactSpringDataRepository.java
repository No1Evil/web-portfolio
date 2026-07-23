package dev.tsumakov.infrastructure.profile.persistence.repository;

import dev.tsumakov.infrastructure.core.persistence.entity.UserEntity;
import dev.tsumakov.infrastructure.profile.persistence.entity.UserContactEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserContactSpringDataRepository extends JpaRepository<UserContactEntity, Integer> {

  List<UserContactEntity> findAllByUserId(UUID id);

  void deleteByIdAndUserId(Integer id, UUID userId);
}
