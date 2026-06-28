package dev.tsumakov.application.portfolio.mapper;

import dev.tsumakov.application.portfolio.dto.api.UserProjectDto;
import dev.tsumakov.domain.portfolio.model.UserProject;
import java.util.Collection;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = SkillDtoMapper.class, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserProjectDtoMapper {

  UserProjectDto toDto(UserProject project);

  List<UserProjectDto> toDtoList(Collection<UserProject> projects);
}
