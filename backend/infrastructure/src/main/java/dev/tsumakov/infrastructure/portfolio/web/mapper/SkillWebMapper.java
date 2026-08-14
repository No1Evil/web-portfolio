package dev.tsumakov.infrastructure.portfolio.web.mapper;

import dev.tsumakov.application.portfolio.dto.api.SkillDto;
import dev.tsumakov.application.portfolio.dto.in.CreateSkillDto;
import dev.tsumakov.infrastructure.portfolio.web.dto.skill.CreateSkillRequest;
import dev.tsumakov.infrastructure.portfolio.web.dto.skill.SkillResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface SkillWebMapper {
  SkillResponse toResponse(SkillDto dto);

  CreateSkillDto toDto(CreateSkillRequest request);
}
