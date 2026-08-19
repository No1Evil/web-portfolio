package dev.tsumakov.infrastructure.core.skillcategory.web.mapper;

import dev.tsumakov.application.core.skillcategory.dto.in.CreateSkillCategoryDto;
import dev.tsumakov.application.core.skillcategory.dto.in.UpdateSkillCategoryDto;
import dev.tsumakov.application.core.skillcategory.dto.outer.SkillCategoryDto;
import dev.tsumakov.infrastructure.core.skillcategory.web.dto.request.CreateSkillCategoryRequest;
import dev.tsumakov.infrastructure.core.skillcategory.web.dto.request.UpdateSkillCategoryRequest;
import dev.tsumakov.infrastructure.core.skillcategory.web.dto.response.SkillCategoryAdminResponse;
import dev.tsumakov.infrastructure.core.skillcategory.web.dto.response.SkillCategoryUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface SkillCategoryWebMapper {

  CreateSkillCategoryDto toDto(CreateSkillCategoryRequest request);

  UpdateSkillCategoryDto toDto(Integer skillCategoryId, UpdateSkillCategoryRequest request);

  SkillCategoryAdminResponse toAdminResponse(SkillCategoryDto dto);

  SkillCategoryUserResponse toUserResponse(SkillCategoryDto dto);

}
