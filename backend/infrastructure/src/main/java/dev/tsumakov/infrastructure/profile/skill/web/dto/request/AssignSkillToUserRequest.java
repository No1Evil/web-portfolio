package dev.tsumakov.infrastructure.profile.skill.web.dto.request;

import jakarta.validation.constraints.NotNull;

public record AssignSkillToUserRequest(
    @NotNull Integer userId,
    @NotNull Integer skillId
) {

}