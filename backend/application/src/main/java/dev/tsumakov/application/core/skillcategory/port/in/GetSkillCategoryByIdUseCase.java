package dev.tsumakov.application.core.skillcategory.port.in;

import dev.tsumakov.application.portfolio.dto.api.SkillCategoryDto;

public interface GetSkillCategoryByIdUseCase {

  SkillCategoryDto execute(Integer categoryId);

}
