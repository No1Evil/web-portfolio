package dev.tsumakov.application.portfolio.port.in.skill;

import dev.tsumakov.application.portfolio.dto.api.SkillDto;
import java.util.Optional;

public interface GetSkillByIdUseCase {
  Optional<SkillDto> execute(Integer id);
}
