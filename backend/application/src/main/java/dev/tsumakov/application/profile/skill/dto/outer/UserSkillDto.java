package dev.tsumakov.application.profile.skill.dto.outer;

public record UserSkillDto(
    Integer id,
    Integer categoryId,
    String name,
    String iconUrl
) {

}
