package dev.tsumakov.application.core.skill.dto.in;

public record UpdateSkillDto(
    Integer skillId,
    Integer categoryId,
    String name,
    String iconUrl
) {

}
