package dev.tsumakov.application.portfolio.mapper;

import dev.tsumakov.application.portfolio.dto.api.SkillDto;
import dev.tsumakov.domain.portfolio.model.Skill;
import java.util.Collection;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface SkillDtoMapper {

  SkillDtoMapper INSTANCE = Mappers.getMapper(SkillDtoMapper.class);

  SkillDto toDto(Skill skill);

  List<SkillDto> toDtoList(Collection<Skill> skills);
}
