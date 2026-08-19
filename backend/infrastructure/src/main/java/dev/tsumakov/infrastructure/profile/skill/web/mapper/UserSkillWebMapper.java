package dev.tsumakov.infrastructure.profile.skill.web.mapper;

import dev.tsumakov.application.profile.skill.dto.outer.UserSkillDto;
import dev.tsumakov.infrastructure.profile.skill.web.dto.response.UserSkillResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface UserSkillWebMapper {

  UserSkillResponse toResponse(UserSkillDto dto);

}