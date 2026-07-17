package dev.tsumakov.application.profile.mapper;

import dev.tsumakov.application.profile.dto.api.EducationDto;
import dev.tsumakov.domain.profile.model.Education;
import java.util.Collection;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface EducationDtoMapper {

  EducationDto toDto(Education education);

  List<EducationDto> toDtoList(Collection<Education> educations);
}
