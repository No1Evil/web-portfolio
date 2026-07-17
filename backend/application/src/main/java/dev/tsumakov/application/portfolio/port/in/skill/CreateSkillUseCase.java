package dev.tsumakov.application.portfolio.port.in.skill;

import dev.tsumakov.application.portfolio.dto.in.CreateSkillDto;
import dev.tsumakov.application.portfolio.dto.api.SkillDto;

public interface CreateSkillUseCase {
  SkillDto execute(CreateSkillDto command);
}
