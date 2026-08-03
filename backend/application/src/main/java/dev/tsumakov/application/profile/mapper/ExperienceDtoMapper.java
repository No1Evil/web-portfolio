package dev.tsumakov.application.profile.mapper;

import dev.tsumakov.application.profile.dto.api.ExperienceDto;
import dev.tsumakov.domain.profile.model.Experience;
import java.util.Collection;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ExperienceDtoMapper {

  ExperienceDtoMapper INSTANCE = Mappers.getMapper(ExperienceDtoMapper.class);

  ExperienceDto toDto(Experience experience);

  List<ExperienceDto> toDtoList(Collection<Experience> experiences);
}
