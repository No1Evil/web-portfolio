package dev.tsumakov.infrastructure.portfolio.persistence.repository;

import dev.tsumakov.infrastructure.portfolio.persistence.entity.SkillEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillSpringDataRepository extends JpaRepository<SkillEntity, Integer> {

  List<SkillEntity> findBySkillCategory_id(Integer skillCategoryId);

  Optional<SkillEntity> findByName(String name);
}
