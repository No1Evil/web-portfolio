package dev.tsumakov.application.core.skillcategory.port.in;

import dev.tsumakov.application.portfolio.dto.api.SkillCategoryDto;
import dev.tsumakov.application.portfolio.dto.in.CreateSkillCategoryDto;

public interface CreateSkillCategoryUseCase {

  SkillCategoryDto execute(CreateSkillCategoryDto command);

}
