package dev.tsumakov.application.profile.skill.usecase;

import dev.tsumakov.application.profile.skill.dto.in.UnassignSkillFromUserDto;
import dev.tsumakov.application.profile.skill.port.in.UnassignSkillFromUserUseCase;
import dev.tsumakov.domain.core.skill.repository.UserSkillRepository;

public class UnassignSkillFromUserUseCaseImpl implements UnassignSkillFromUserUseCase {

  private final UserSkillRepository repository;

  public UnassignSkillFromUserUseCaseImpl(UserSkillRepository repository) {
    this.repository = repository;
  }

  @Override
  public void execute(UnassignSkillFromUserDto command) {
    repository.removeSkill(command.userId(), command.skillId());
  }
}
