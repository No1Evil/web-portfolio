package dev.tsumakov.application.profile.contact.mapper;

import dev.tsumakov.application.profile.contact.dto.outer.UserContactDto;
import dev.tsumakov.domain.profile.contact.model.UserContact;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserContactDtoMapper {

  UserContactDtoMapper INSTANCE = Mappers.getMapper(UserContactDtoMapper.class);

  UserContactDto toDto(UserContact userContact);

}
