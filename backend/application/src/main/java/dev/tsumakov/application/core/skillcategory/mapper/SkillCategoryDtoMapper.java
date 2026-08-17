package dev.tsumakov.application.core.skillcategory.mapper;

import dev.tsumakov.application.core.skillcategory.dto.outer.SkillCategoryDto;
import dev.tsumakov.domain.core.skillcategory.model.SkillCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SkillCategoryDtoMapper {

  SkillCategoryDtoMapper INSTANCE = Mappers.getMapper(SkillCategoryDtoMapper.class);

  SkillCategoryDto toDto(SkillCategory skillCategory);
}
