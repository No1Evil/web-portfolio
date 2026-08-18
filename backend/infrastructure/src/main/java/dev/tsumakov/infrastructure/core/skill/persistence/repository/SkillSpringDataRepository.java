package dev.tsumakov.infrastructure.core.skill.persistence.repository;

import dev.tsumakov.infrastructure.core.skill.persistence.entity.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillSpringDataRepository extends JpaRepository<SkillEntity, Integer> {

}
