package dev.tsumakov.application.profile.skill.mapper;

import dev.tsumakov.application.profile.skill.dto.outer.UserSkillDto;
import dev.tsumakov.domain.core.skill.model.Skill;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserSkillDtoMapper {

  UserSkillDtoMapper INSTANCE = Mappers.getMapper(UserSkillDtoMapper.class);

  UserSkillDto toDto(Skill skill);

}
