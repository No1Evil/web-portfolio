package dev.tsumakov.infrastructure.core.user.web.mapper;

import dev.tsumakov.application.core.user.dto.in.AuthenticateUserDto;
import dev.tsumakov.application.core.user.dto.in.UpdateUserPasswordDto;
import dev.tsumakov.application.core.user.dto.outer.UserDto;
import dev.tsumakov.infrastructure.core.user.web.dto.request.AuthenticateUserRequest;
import dev.tsumakov.infrastructure.core.user.web.dto.request.UpdateUserPasswordRequest;
import dev.tsumakov.infrastructure.core.user.web.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface UserWebMapper {

  UpdateUserPasswordDto toDto(Integer userId, UpdateUserPasswordRequest request);

  AuthenticateUserDto toDto(AuthenticateUserRequest request);

  UserResponse toResponse(UserDto dto);
}
