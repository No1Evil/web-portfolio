package dev.tsumakov.application.core.skill.usecase;

import dev.tsumakov.application.core.skill.port.in.DeleteSkillUseCase;
import dev.tsumakov.domain.core.skill.repository.SkillRepository;

public class DeleteSkillUseCaseImpl implements DeleteSkillUseCase {

  private final SkillRepository skillRepository;

  public DeleteSkillUseCaseImpl(SkillRepository skillRepository) {
    this.skillRepository = skillRepository;
  }

  @Override
  public void delete(Integer skillId) {
    skillRepository.delete(skillId);
  }
}
