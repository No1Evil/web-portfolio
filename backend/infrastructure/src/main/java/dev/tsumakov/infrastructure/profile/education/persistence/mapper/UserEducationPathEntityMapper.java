package dev.tsumakov.infrastructure.profile.education.persistence.mapper;

import dev.tsumakov.domain.profile.education.model.UserEducationPath;
import dev.tsumakov.infrastructure.profile.education.persistence.entity.UserEducationPathEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface UserEducationPathEntityMapper {

  UserEducationPathEntity toEntity(UserEducationPath domain);

  UserEducationPath toDomain(UserEducationPathEntity entity);

}
