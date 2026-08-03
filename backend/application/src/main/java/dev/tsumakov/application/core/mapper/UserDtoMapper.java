package dev.tsumakov.application.core.mapper;

import dev.tsumakov.application.core.dto.api.UserDto;
import dev.tsumakov.domain.core.model.User;
import java.util.Collection;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = RoleDtoMapper.class, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserDtoMapper {

  UserDtoMapper INSTANCE = Mappers.getMapper(UserDtoMapper.class);

  UserDto toDto(User user);

  List<UserDto> toDtoList(Collection<User> users);
}
