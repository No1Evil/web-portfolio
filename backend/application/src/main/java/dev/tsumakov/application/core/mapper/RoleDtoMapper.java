package dev.tsumakov.application.core.mapper;

import dev.tsumakov.application.core.dto.api.RoleDto;
import dev.tsumakov.domain.core.model.Role;
import java.util.Collection;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RoleDtoMapper {

  RoleDtoMapper INSTANCE = Mappers.getMapper(RoleDtoMapper.class);

  RoleDto toDto(Role role);

  List<RoleDto> toDtoList(Collection<Role> roles);
}
