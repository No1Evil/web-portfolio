package dev.tsumakov.application.core.skillcategory.port.in;

import dev.tsumakov.application.core.skillcategory.dto.in.UpdateSkillCategoryDto;
import dev.tsumakov.application.core.skillcategory.dto.outer.SkillCategoryDto;

public interface UpdateSkillCategoryUseCase {

  SkillCategoryDto execute(UpdateSkillCategoryDto command);

}
