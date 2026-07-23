package dev.tsumakov.application.portfolio.mapper;

import dev.tsumakov.application.portfolio.dto.api.UserProjectDto;
import dev.tsumakov.domain.portfolio.model.UserProject;
import java.util.Collection;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(uses = SkillDtoMapper.class, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserProjectDtoMapper {

  UserProjectDtoMapper INSTANCE = Mappers.getMapper(UserProjectDtoMapper.class);

  UserProjectDto toDto(UserProject project);

  List<UserProjectDto> toDtoList(Collection<UserProject> projects);
}
