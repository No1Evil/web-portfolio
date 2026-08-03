package dev.tsumakov.application.profile.mapper;

import dev.tsumakov.application.profile.dto.api.UserProfileDto;
import dev.tsumakov.domain.profile.model.UserProfile;
import java.util.Collection;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserProfileDtoMapper {

  UserProfileDtoMapper INSTANCE = Mappers.getMapper(UserProfileDtoMapper.class);

  UserProfileDto toDto(UserProfile profile);

  List<UserProfileDto> toDtoList(Collection<UserProfile> profiles);
}
