package dev.tsumakov.application.portfolio.usecase.skill;

import dev.tsumakov.application.portfolio.port.in.skill.DeleteSkillUseCase;
import dev.tsumakov.domain.portfolio.repository.SkillRepository;

public class DeleteSkillUseCaseImpl implements DeleteSkillUseCase {

  private final SkillRepository skillRepository;

  public DeleteSkillUseCaseImpl(SkillRepository skillRepository) {
    this.skillRepository = skillRepository;
  }

  @Override
  public void execute(Integer id) {
    skillRepository.deleteById(id);
  }
}
