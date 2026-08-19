package dev.tsumakov.application.profile.skill.port.in;

import dev.tsumakov.application.profile.skill.dto.in.UnassignSkillFromUserDto;

public interface UnassignSkillFromUserUseCase {

  void execute(UnassignSkillFromUserDto command);

}
