package dev.tsumakov.application.profile.mapper;

import dev.tsumakov.application.profile.dto.api.UserContactDto;
import dev.tsumakov.domain.profile.model.UserContact;
import java.util.Collection;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserContactDtoMapper {

  UserContactDtoMapper INSTANCE = Mappers.getMapper(UserContactDtoMapper.class);

  UserContactDto toDto(UserContact contact);

  List<UserContactDto> toDtoList(Collection<UserContact> contacts);
}
