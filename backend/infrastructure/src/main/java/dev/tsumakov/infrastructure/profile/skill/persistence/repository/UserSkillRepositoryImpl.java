package dev.tsumakov.infrastructure.profile.skill.persistence.repository;

import dev.tsumakov.domain.core.skill.model.Skill;
import dev.tsumakov.domain.core.skill.repository.UserSkillRepository;
import dev.tsumakov.infrastructure.core.skill.persistence.mapper.SkillEntityMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserSkillRepositoryImpl implements UserSkillRepository {

  private final UserSkillSpringDataRepository repository;
  private final SkillEntityMapper mapper;

  @Override
  public void addSkill(Integer userId, Integer skillId) {
    repository.addSkill(userId, skillId);
  }

  @Override
  public void removeSkill(Integer userId, Integer skillId) {
    repository.removeSkill(userId, skillId);
  }

  @Override
  public List<Skill> findAllByUserId(Integer userId) {
    return repository.findAllByUserId(userId).stream().map(mapper::toDomain).toList();
  }

  @Override
  public boolean exists(Integer userId, Integer skillId) {
    return repository.exists(userId, skillId);
  }
}
