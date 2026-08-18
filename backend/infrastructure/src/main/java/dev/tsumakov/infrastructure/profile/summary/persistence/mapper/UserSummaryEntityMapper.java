package dev.tsumakov.infrastructure.profile.summary.persistence.mapper;

import dev.tsumakov.domain.profile.summary.model.UserSummary;
import dev.tsumakov.infrastructure.profile.summary.persistence.entity.UserSummaryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface UserSummaryEntityMapper {

  UserSummaryEntity toEntity(UserSummary domain);

  UserSummary toDomain(UserSummaryEntity entity);

}
