package dev.tsumakov.infrastructure.profile.experience.web.mapper;

import dev.tsumakov.application.profile.experience.dto.in.CreateUserExperiencePathDto;
import dev.tsumakov.application.profile.experience.dto.in.UpdateUserExperiencePathDto;
import dev.tsumakov.application.profile.experience.dto.outer.UserExperiencePathDto;
import dev.tsumakov.infrastructure.profile.experience.web.dto.request.CreateUserExperiencePathRequest;
import dev.tsumakov.infrastructure.profile.experience.web.dto.request.UpdateUserExperiencePathRequest;
import dev.tsumakov.infrastructure.profile.experience.web.dto.response.UserExperiencePathAdminResponse;
import dev.tsumakov.infrastructure.profile.experience.web.dto.response.UserExperiencePathResponse;
import java.util.UUID;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface UserExperiencePathWebMapper {

  CreateUserExperiencePathDto toDto(CreateUserExperiencePathRequest request);

  UpdateUserExperiencePathDto toDto(UUID userExperiencePathId, UpdateUserExperiencePathRequest request);

  UserExperiencePathAdminResponse toAdminResponse(UserExperiencePathDto dto);

  UserExperiencePathResponse toResponse(UserExperiencePathDto dto);

}