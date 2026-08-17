package dev.tsumakov.application.core.skillcategory.port.in;

import dev.tsumakov.application.portfolio.dto.api.SkillCategoryDto;
import java.util.List;

public interface GetAllSkillCategoriesUseCase {

  List<SkillCategoryDto> execute();

}
