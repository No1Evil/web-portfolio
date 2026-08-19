package dev.tsumakov.application.profile.skill.dto.in;

public record AssignSkillToUserDto(
    Integer userId,
    Integer skillId
) {

}
