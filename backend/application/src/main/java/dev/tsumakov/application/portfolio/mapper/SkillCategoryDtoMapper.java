package dev.tsumakov.application.portfolio.mapper;

import dev.tsumakov.application.portfolio.dto.api.SkillCategoryDto;
import dev.tsumakov.domain.portfolio.model.SkillCategory;
import java.util.Collection;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface SkillCategoryDtoMapper {

  SkillCategoryDtoMapper INSTANCE = Mappers.getMapper(SkillCategoryDtoMapper.class);

  SkillCategoryDto toDto(SkillCategory category);

  List<SkillCategoryDto> toDtoList(Collection<SkillCategory> categories);
}
