package dev.tsumakov.infrastructure.core.persistence.mapper;

import dev.tsumakov.domain.core.model.Role;
import dev.tsumakov.infrastructure.core.persistence.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {
  Role toDomain(RoleEntity role);

  RoleEntity toEntity(Role role);
}
