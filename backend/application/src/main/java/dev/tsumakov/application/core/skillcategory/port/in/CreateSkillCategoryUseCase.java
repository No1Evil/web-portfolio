package dev.tsumakov.application.core.skillcategory.port.in;


import dev.tsumakov.application.core.skillcategory.dto.in.CreateSkillCategoryDto;
import dev.tsumakov.application.core.skillcategory.dto.outer.SkillCategoryDto;

public interface CreateSkillCategoryUseCase {

  SkillCategoryDto execute(CreateSkillCategoryDto command);

}
