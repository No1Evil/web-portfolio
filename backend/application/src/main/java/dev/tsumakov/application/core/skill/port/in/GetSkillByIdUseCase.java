package dev.tsumakov.application.core.skill.port.in;

import dev.tsumakov.application.core.skill.dto.outer.SkillDto;

public interface GetSkillByIdUseCase {

  SkillDto execute(Integer skillId);

}
