package dev.tsumakov.application.profile.skill.port.in;

import dev.tsumakov.application.profile.skill.dto.outer.UserSkillDto;
import java.util.List;

public interface GetAllUserSkillsUseCase {

  List<UserSkillDto> execute();

}
