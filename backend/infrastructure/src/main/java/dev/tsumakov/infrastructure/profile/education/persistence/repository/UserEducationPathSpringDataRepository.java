package dev.tsumakov.infrastructure.profile.education.persistence.repository;

import dev.tsumakov.infrastructure.profile.education.persistence.entity.UserEducationPathEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEducationPathSpringDataRepository
    extends JpaRepository<UserEducationPathEntity, UUID> {

}
