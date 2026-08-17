package dev.tsumakov.domain.core.skill.repository;

import dev.tsumakov.domain.core.skill.model.Skill;
import java.util.List;

public interface UserSkillRepository {

  /**
   * Add skill to a user
   */
  void addSkill(Integer userId, Integer skillId);

  /**
   * Remove skill from a user
   */
  void removeSkill(Integer userId, Integer skillId);

  List<Skill> findAllByUserId(Integer userId);

  /**
   * @return does user have skill
   */
  boolean exists(Integer userId, Integer skillId);

}
