package dev.tsumakov.application.profile.mapper;

import dev.tsumakov.application.profile.dto.api.ExperienceDto;
import dev.tsumakov.domain.profile.model.Experience;
import java.util.Collection;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ExperienceDtoMapper {

  ExperienceDto toDto(Experience experience);

  List<ExperienceDto> toDtoList(Collection<Experience> experiences);
}
