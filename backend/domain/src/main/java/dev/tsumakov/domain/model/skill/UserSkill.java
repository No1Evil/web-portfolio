package dev.tsumakov.domain.model.skill;

import java.util.Objects;
import java.util.UUID;

public record UserSkill(
    UUID skillItemId,
    boolean isPrimary
) {

  public UserSkill {
    Objects.requireNonNull(skillItemId);
  }
}
