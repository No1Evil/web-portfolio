package dev.tsumakov.infrastructure.core.skillcategory.persistence.mapper;

import dev.tsumakov.domain.core.skillcategory.model.SkillCategory;
import dev.tsumakov.infrastructure.core.skillcategory.persistence.entity.SkillCategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface SkillCategoryEntityMapper {

  SkillCategory toDomain(SkillCategoryEntity entity);

  SkillCategoryEntity toEntity(SkillCategory domain);

}
