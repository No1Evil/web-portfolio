package dev.tsumakov.application.portfolio.mapper;

import dev.tsumakov.application.portfolio.dto.api.SkillCategoryDto;
import dev.tsumakov.domain.portfolio.model.SkillCategory;
import java.util.Collection;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface SkillCategoryDtoMapper {

  SkillCategoryDto toDto(SkillCategory category);

  List<SkillCategoryDto> toDtoList(Collection<SkillCategory> categories);
}
