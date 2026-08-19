package dev.tsumakov.application.profile.summary.mapper;

import dev.tsumakov.application.profile.summary.dto.outer.UserSummaryDto;
import dev.tsumakov.domain.profile.summary.model.UserSummary;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserSummaryDtoMapper {

  UserSummaryDtoMapper INSTANCE = Mappers.getMapper(UserSummaryDtoMapper.class);

  UserSummaryDto toDto(UserSummary userSummary);

}
