package dev.tsumakov.infrastructure.profile.skill.web.dto.request;

import jakarta.validation.constraints.NotNull;

public record UnassignSkillFromUserRequest(
    @NotNull Integer userId,
    @NotNull Integer skillId
) {

}