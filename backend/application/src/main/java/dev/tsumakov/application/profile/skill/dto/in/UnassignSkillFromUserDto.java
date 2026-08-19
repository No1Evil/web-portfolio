package dev.tsumakov.application.profile.skill.dto.in;

public record UnassignSkillFromUserDto(
    Integer userId,
    Integer skillId
) {

}
