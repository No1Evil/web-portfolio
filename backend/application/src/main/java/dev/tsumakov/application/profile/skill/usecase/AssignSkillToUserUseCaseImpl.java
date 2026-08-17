package dev.tsumakov.application.profile.skill.usecase;

import dev.tsumakov.application.profile.skill.dto.in.AssignSkillToUserDto;
import dev.tsumakov.application.profile.skill.port.in.AssignSkillToUserUseCase;
import dev.tsumakov.domain.core.skill.repository.UserSkillRepository;

public class AssignSkillToUserUseCaseImpl implements AssignSkillToUserUseCase {

  private final UserSkillRepository repository;

  public AssignSkillToUserUseCaseImpl(UserSkillRepository repository) {
    this.repository = repository;
  }

  @Override
  public void execute(AssignSkillToUserDto command) {
    repository.addSkill(command.userId(), command.skillId());
  }
}
