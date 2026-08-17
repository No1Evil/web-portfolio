package dev.tsumakov.application.profile.skill.port.in;

import dev.tsumakov.application.profile.skill.dto.in.AssignSkillToUserDto;

public interface AssignSkillToUserUseCase {

  void execute(AssignSkillToUserDto command);

}
