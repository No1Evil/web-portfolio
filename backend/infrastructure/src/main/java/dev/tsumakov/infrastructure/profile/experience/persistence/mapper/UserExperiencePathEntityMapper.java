package dev.tsumakov.infrastructure.profile.experience.persistence.mapper;

import dev.tsumakov.domain.profile.experience.model.UserExperiencePath;
import dev.tsumakov.infrastructure.profile.experience.persistence.entity.UserExperiencePathEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface UserExperiencePathEntityMapper {

  UserExperiencePathEntity toEntity(UserExperiencePath domain);

  UserExperiencePath toDomain(UserExperiencePathEntity entity);

}
