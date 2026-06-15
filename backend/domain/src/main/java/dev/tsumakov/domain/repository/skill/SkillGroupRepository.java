package dev.tsumakov.domain.repository.skill;

import dev.tsumakov.domain.model.skill.group.SkillGroup;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

public interface SkillGroupRepository {

  Optional<SkillGroup> findById(UUID id);

  Optional<SkillGroup> findLocalizedById(UUID id, Locale locale);

  List<SkillGroup> findAll();

  void save(SkillGroup group);

  void delete(UUID id);
}
