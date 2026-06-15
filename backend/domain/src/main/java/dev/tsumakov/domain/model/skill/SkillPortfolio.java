package dev.tsumakov.domain.model.skill;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public record SkillPortfolio(
    UUID id,
    UUID userId,
    List<UserSkill> skills
) {

  public SkillPortfolio {
    Objects.requireNonNull(id);
    Objects.requireNonNull(userId);

    skills = List.copyOf(skills);
  }
}
