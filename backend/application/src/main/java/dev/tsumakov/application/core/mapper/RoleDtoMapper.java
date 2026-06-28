package dev.tsumakov.application.core.mapper;

import dev.tsumakov.application.core.dto.api.RoleDto;
import dev.tsumakov.domain.core.model.Role;
import java.util.Collection;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RoleDtoMapper {

  RoleDto toDto(Role role);

  List<RoleDto> toDtoList(Collection<Role> roles);
}
