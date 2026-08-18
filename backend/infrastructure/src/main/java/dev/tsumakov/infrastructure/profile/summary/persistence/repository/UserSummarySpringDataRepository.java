package dev.tsumakov.infrastructure.profile.summary.persistence.repository;

import dev.tsumakov.infrastructure.profile.summary.persistence.entity.UserSummaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSummarySpringDataRepository extends JpaRepository<UserSummaryEntity, Integer> {

}
