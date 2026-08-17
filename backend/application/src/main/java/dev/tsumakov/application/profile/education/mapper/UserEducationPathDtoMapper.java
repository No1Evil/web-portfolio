package dev.tsumakov.application.profile.education.mapper;

import dev.tsumakov.application.profile.education.dto.outer.UserEducationPathDto;
import dev.tsumakov.domain.profile.education.model.UserEducationPath;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserEducationPathDtoMapper {

  UserEducationPathDtoMapper INSTANCE = Mappers.getMapper(UserEducationPathDtoMapper.class);

  UserEducationPathDto toDto(UserEducationPath userEducationPath);

}
