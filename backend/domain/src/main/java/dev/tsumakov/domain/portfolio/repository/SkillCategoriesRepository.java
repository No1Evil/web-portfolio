package dev.tsumakov.domain.portfolio.repository;

import dev.tsumakov.domain.portfolio.model.SkillCategory;
import java.util.List;
import java.util.Optional;

public interface SkillCategoriesRepository {

  List<SkillCategory> findAll();

  Optional<SkillCategory> findById(Integer id);

  Optional<SkillCategory> findByName(String name);

  void save(SkillCategory skillCategory);

  void delete(SkillCategory skillCategory);

  void deleteById(Integer id);
}
