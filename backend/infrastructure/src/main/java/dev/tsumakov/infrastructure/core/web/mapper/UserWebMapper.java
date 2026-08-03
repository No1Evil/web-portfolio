package dev.tsumakov.infrastructure.core.web.mapper;

import dev.tsumakov.application.core.dto.api.UserDto;
import dev.tsumakov.application.core.dto.in.CreateUserDto;
import dev.tsumakov.infrastructure.core.web.dto.user.CreateUserRequest;
import dev.tsumakov.infrastructure.core.web.dto.user.UserCreatedResponse;
import dev.tsumakov.infrastructure.core.web.dto.user.UserResponse;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface UserWebMapper {

  CreateUserDto toDto(CreateUserRequest request);

  UserCreatedResponse toCreatedResponse(UserDto dto);

  UserResponse toResponse(UserDto dto);

  List<UserResponse> toCreatedResponse(List<UserDto> dtoList);

}
