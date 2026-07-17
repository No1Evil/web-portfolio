package dev.tsumakov.application.portfolio.port.in.skillcategory;

import dev.tsumakov.application.portfolio.dto.api.SkillCategoryDto;
import java.util.List;

public interface GetAllSkillCategoriesUseCase {
  List<SkillCategoryDto> execute();
}
