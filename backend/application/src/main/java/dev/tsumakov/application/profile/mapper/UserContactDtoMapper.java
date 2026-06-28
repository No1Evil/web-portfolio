package dev.tsumakov.application.profile.mapper;

import dev.tsumakov.application.profile.dto.api.UserContactDto;
import dev.tsumakov.domain.profile.model.UserContact;
import java.util.Collection;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserContactDtoMapper {

  UserContactDto toDto(UserContact contact);

  List<UserContactDto> toDtoList(Collection<UserContact> contacts);
}
