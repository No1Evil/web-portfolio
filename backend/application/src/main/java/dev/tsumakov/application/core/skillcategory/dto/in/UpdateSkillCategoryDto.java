package dev.tsumakov.application.core.skillcategory.dto.in;

public record UpdateSkillCategoryDto(
  Integer skillCategoryId,
  String name,
  String iconUrl
) {

}
