package dev.tsumakov.infrastructure.core.skill.persistence.mapper;

import dev.tsumakov.domain.core.skill.model.Skill;
import dev.tsumakov.infrastructure.core.skill.persistence.entity.SkillEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface SkillEntityMapper {

  @Mapping(target = "categoryId", source = "skillCategory.id")
  Skill toDomain(SkillEntity entity);

  @Mapping(target = "skillCategory.id", source = "categoryId")
  SkillEntity toEntity(Skill domain);
}
