package dev.tsumakov.infrastructure.core.persistence.mapper;

import dev.tsumakov.domain.core.model.User;
import dev.tsumakov.infrastructure.core.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

  User toDomain(UserEntity user);

  UserEntity toEntity(User user);
}
