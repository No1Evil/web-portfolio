package dev.tsumakov.application.portfolio.port.in.skill;

import dev.tsumakov.application.portfolio.dto.api.SkillDto;
import java.util.List;

public interface GetSkillsByCategoryUseCase {
  List<SkillDto> execute(Integer categoryId);
}
