package dev.tsumakov.domain.repository.skill;

import dev.tsumakov.domain.model.skill.item.Skill;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SkillRepository {
  Optional<Skill> findById(UUID id);

  List<Skill> findAll();

  List<Skill> findByGroupId(UUID skillGroupId);

  void save(Skill skill);

  void delete(UUID id);
}
