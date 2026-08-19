package dev.tsumakov.application.core.user.mapper;

import dev.tsumakov.application.core.user.dto.outer.UserDto;
import dev.tsumakov.domain.core.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserDtoMapper {

  UserDtoMapper INSTANCE = Mappers.getMapper(UserDtoMapper.class);

  UserDto toDto(User user);

}
