package dev.tsumakov.application.profile.experience.mapper;

import dev.tsumakov.application.profile.experience.dto.outer.UserExperiencePathDto;
import dev.tsumakov.domain.profile.experience.model.UserExperiencePath;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserExperiencePathDtoMapper {

  UserExperiencePathDtoMapper INSTANCE = Mappers.getMapper(UserExperiencePathDtoMapper.class);

  UserExperiencePathDto toDto(UserExperiencePath userExperiencePath);

}
