package dev.tsumakov.infrastructure.profile.education.web.mapper;

import dev.tsumakov.application.profile.education.dto.in.CreateUserEducationPathDto;
import dev.tsumakov.application.profile.education.dto.in.UpdateUserEducationPathDto;
import dev.tsumakov.application.profile.education.dto.outer.UserEducationPathDto;
import dev.tsumakov.infrastructure.profile.education.web.dto.request.CreateUserEducationPathRequest;
import dev.tsumakov.infrastructure.profile.education.web.dto.request.UpdateUserEducationPathRequest;
import dev.tsumakov.infrastructure.profile.education.web.dto.response.UserEducationPathAdminResponse;
import dev.tsumakov.infrastructure.profile.education.web.dto.response.UserEducationPathResponse;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface UserEducationPathWebMapper {

  CreateUserEducationPathDto toDto(CreateUserEducationPathRequest request);

  UpdateUserEducationPathDto toDto(UUID userEducationPathId, UpdateUserEducationPathRequest request);

  UserEducationPathAdminResponse toAdminResponse(UserEducationPathDto dto);

  UserEducationPathResponse toResponse(UserEducationPathDto dto);

}