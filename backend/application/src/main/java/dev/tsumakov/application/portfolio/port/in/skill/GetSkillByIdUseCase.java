package dev.tsumakov.application.portfolio.port.in.skill;

import dev.tsumakov.application.portfolio.dto.api.SkillDto;

public interface GetSkillByIdUseCase {
  SkillDto execute(Integer id);
}
