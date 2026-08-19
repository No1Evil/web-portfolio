package dev.tsumakov.infrastructure.profile.experience.persistence.repository;

import dev.tsumakov.infrastructure.profile.experience.persistence.entity.UserExperiencePathEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserExperiencePathSpringDataRepository
    extends JpaRepository<UserExperiencePathEntity, UUID> {

}
