package dev.tsumakov.application.portfolio.port.in.skillcategory;

import dev.tsumakov.application.portfolio.dto.api.SkillCategoryDto;
import java.util.Optional;

public interface GetSkillCategoryByIdUseCase {
  Optional<SkillCategoryDto> execute(Integer id);
}
