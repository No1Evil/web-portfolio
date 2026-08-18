package dev.tsumakov.infrastructure.core.skillcategory.persistence.repository;

import dev.tsumakov.infrastructure.core.skillcategory.persistence.entity.SkillCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillCategorySpringDataRepository extends JpaRepository<SkillCategoryEntity, Integer> {

}
