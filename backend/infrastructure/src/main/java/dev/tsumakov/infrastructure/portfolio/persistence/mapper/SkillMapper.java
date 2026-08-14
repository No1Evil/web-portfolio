package dev.tsumakov.infrastructure.portfolio.persistence.mapper;

import dev.tsumakov.domain.portfolio.model.Skill;
import dev.tsumakov.infrastructure.portfolio.persistence.entity.SkillEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface SkillMapper {

  Skill toDomain(SkillEntity skillEntity);

  SkillEntity toEntity(Skill skill);
}
