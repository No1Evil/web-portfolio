package dev.tsumakov.infrastructure.profile.contact.persistence.mapper;

import dev.tsumakov.domain.profile.contact.model.UserContact;
import dev.tsumakov.infrastructure.profile.contact.persistence.entity.UserContactEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface UserContactEntityMapper {

  UserContactEntity toEntity(UserContact domain);

  UserContact toDomain(UserContactEntity entity);

}
