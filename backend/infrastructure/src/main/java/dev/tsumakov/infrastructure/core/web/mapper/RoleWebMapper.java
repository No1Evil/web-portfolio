package dev.tsumakov.infrastructure.core.web.mapper;

import dev.tsumakov.application.core.dto.api.RoleDto;
import dev.tsumakov.application.core.dto.in.CreateRoleDto;
import dev.tsumakov.infrastructure.core.web.dto.role.CreateRoleRequest;
import dev.tsumakov.infrastructure.core.web.dto.role.RoleCreatedResponse;
import dev.tsumakov.infrastructure.core.web.dto.role.RoleResponse;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface RoleWebMapper {

  CreateRoleDto toDto(CreateRoleRequest request);
  RoleCreatedResponse toResponse(RoleDto dto);

  List<RoleResponse> toResponseList(List<RoleDto> dtoList);
}
