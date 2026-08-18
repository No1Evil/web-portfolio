package dev.tsumakov.infrastructure.profile.contact.persistence.repository;

import dev.tsumakov.infrastructure.profile.contact.persistence.entity.UserContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserContactSpringDataRepository extends JpaRepository<UserContactEntity, Integer> {

}
