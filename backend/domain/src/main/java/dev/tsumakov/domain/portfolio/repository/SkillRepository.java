package dev.tsumakov.domain.portfolio.repository;

import dev.tsumakov.domain.portfolio.model.Skill;
import java.util.List;
import java.util.Optional;

public interface SkillRepository {

  Optional<Skill> findById(Integer skillId);

  List<Skill> findAll();

  List<Skill> findAllByCategory(Integer skillCategoryId);

  Optional<Skill> findByName(String name);

  void save(Skill skill);

  void delete(Skill skill);

  void deleteById(Integer id);
}
