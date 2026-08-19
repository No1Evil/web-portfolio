package dev.tsumakov.application.core.skill.port.in;

import dev.tsumakov.application.core.skill.dto.in.CreateSkillDto;
import dev.tsumakov.application.core.skill.dto.outer.SkillDto;

public interface CreateSkillUseCase {

  /**
   * @return created skill id
   */
  SkillDto execute(CreateSkillDto command);

}
