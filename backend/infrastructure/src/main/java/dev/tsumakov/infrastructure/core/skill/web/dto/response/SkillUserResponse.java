package dev.tsumakov.infrastructure.core.skill.web.dto.response;

public record SkillUserResponse(
    Integer id,
    Integer categoryId,
    String name,
    String iconUrl
) {

}
