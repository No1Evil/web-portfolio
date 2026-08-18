package dev.tsumakov.infrastructure.profile.summary.web.mapper;

import dev.tsumakov.application.profile.summary.dto.in.UpdateUserSummaryDto;
import dev.tsumakov.application.profile.summary.dto.outer.UserSummaryDto;
import dev.tsumakov.infrastructure.profile.summary.web.dto.request.UpdateUserSummaryRequest;
import dev.tsumakov.infrastructure.profile.summary.web.dto.response.UserSummaryAdminResponse;
import dev.tsumakov.infrastructure.profile.summary.web.dto.response.UserSummaryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface UserSummaryWebMapper {

  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  UpdateUserSummaryDto toDto(UpdateUserSummaryRequest request);

  UserSummaryAdminResponse toAdminResponse(UserSummaryDto dto);

  UserSummaryResponse toResponse(UserSummaryDto dto);

}