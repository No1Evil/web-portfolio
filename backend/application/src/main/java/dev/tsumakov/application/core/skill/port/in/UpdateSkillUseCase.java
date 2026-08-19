package dev.tsumakov.application.core.skill.port.in;

import dev.tsumakov.application.core.skill.dto.in.UpdateSkillDto;
import dev.tsumakov.application.core.skill.dto.outer.SkillDto;

public interface UpdateSkillUseCase {

  SkillDto execute(UpdateSkillDto command);

}
