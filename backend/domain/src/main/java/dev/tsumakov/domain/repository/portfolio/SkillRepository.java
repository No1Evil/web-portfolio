package dev.tsumakov.domain.repository.portfolio;

import dev.tsumakov.domain.model.portfolio.Skill;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SkillRepository {

  Optional<Skill> findById(Integer skillId);

  List<Skill> findAll();

  List<Skill> findAllByCategory(Integer skillCategoryId);

  Optional<Skill> findByName(String name);

  void save(Skill skill);

  void delete(Skill skill);

  void deleteById(Integer id);
}
