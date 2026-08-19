package dev.tsumakov.application.core.skillcategory.port.in;

import dev.tsumakov.application.core.skillcategory.dto.outer.SkillCategoryDto;
import java.util.List;

public interface GetAllSkillCategoriesUseCase {

  List<SkillCategoryDto> execute();

}
