package dev.tsumakov.application.core.skill.mapper;

import dev.tsumakov.application.core.skill.dto.outer.SkillDto;
import dev.tsumakov.domain.core.skill.model.Skill;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SkillDtoMapper {

  SkillDtoMapper INSTANCE = Mappers.getMapper(SkillDtoMapper.class);

  SkillDto toDto(Skill skill);

}
