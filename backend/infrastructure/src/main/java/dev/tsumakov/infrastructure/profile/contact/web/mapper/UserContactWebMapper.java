package dev.tsumakov.infrastructure.profile.contact.web.mapper;

import dev.tsumakov.application.profile.contact.dto.in.CreateUserContactDto;
import dev.tsumakov.application.profile.contact.dto.in.UpdateUserContactDto;
import dev.tsumakov.application.profile.contact.dto.outer.UserContactDto;
import dev.tsumakov.infrastructure.profile.contact.web.dto.request.CreateUserContactRequest;
import dev.tsumakov.infrastructure.profile.contact.web.dto.request.UpdateUserContactRequest;
import dev.tsumakov.infrastructure.profile.contact.web.dto.response.UserContactAdminResponse;
import dev.tsumakov.infrastructure.profile.contact.web.dto.response.UserContactResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface UserContactWebMapper {

  CreateUserContactDto toDto(CreateUserContactRequest request);

  UpdateUserContactDto toDto(Integer userContactId, UpdateUserContactRequest request);

  UserContactAdminResponse toAdminResponse(UserContactDto dto);

  UserContactResponse toResponse(UserContactDto dto);

}