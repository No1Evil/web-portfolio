package dev.tsumakov.application.core.skill.port.in;

import dev.tsumakov.application.core.skill.dto.outer.SkillDto;
import java.util.List;

public interface GetAllSkillsUseCase {

  List<SkillDto> execute();

}
