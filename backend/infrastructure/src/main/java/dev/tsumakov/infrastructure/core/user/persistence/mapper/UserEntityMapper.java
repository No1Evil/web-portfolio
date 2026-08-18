package dev.tsumakov.infrastructure.core.user.persistence.mapper;

import dev.tsumakov.domain.core.user.model.User;
import dev.tsumakov.infrastructure.core.user.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface UserEntityMapper {

  User toDomain(UserEntity entity);

  UserEntity toEntity(User domain);

}
