package dev.tsumakov.infrastructure.profile.skill.web.dto.response;

public record UserSkillResponse(
    Integer id,
    Integer categoryId,
    String name,
    String iconUrl
) {

}