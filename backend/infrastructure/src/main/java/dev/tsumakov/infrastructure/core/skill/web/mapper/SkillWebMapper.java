package dev.tsumakov.infrastructure.core.skill.web.mapper;

import dev.tsumakov.application.core.skill.dto.in.CreateSkillDto;
import dev.tsumakov.application.core.skill.dto.in.UpdateSkillDto;
import dev.tsumakov.application.core.skill.dto.outer.SkillDto;
import dev.tsumakov.infrastructure.core.skill.web.dto.request.CreateSkillRequest;
import dev.tsumakov.infrastructure.core.skill.web.dto.request.UpdateSkillRequest;
import dev.tsumakov.infrastructure.core.skill.web.dto.response.SkillAdminResponse;
import dev.tsumakov.infrastructure.core.skill.web.dto.response.SkillUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface SkillWebMapper {

  CreateSkillDto toDto(CreateSkillRequest request);

  UpdateSkillDto toDto(Integer skillId, UpdateSkillRequest request);

  SkillAdminResponse toAdminResponse(SkillDto dto);

  SkillUserResponse toUserResponse(SkillDto dto);

}
