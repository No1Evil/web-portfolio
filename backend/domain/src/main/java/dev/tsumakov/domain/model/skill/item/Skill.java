package dev.tsumakov.domain.model.skill.item;

import java.util.Objects;
import java.util.UUID;

public record Skill(
    UUID id,
    UUID groupId,
    String name
) {

  public Skill {
    Objects.requireNonNull(id);
    Objects.requireNonNull(groupId);
    Objects.requireNonNull(name);

    if (name.isBlank()) {
      throw new IllegalArgumentException("Skill name can not be blank");
    }
  }

}
