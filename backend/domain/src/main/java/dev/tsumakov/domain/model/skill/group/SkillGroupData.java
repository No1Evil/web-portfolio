package dev.tsumakov.domain.model.skill.group;

import java.util.Locale;
import java.util.Objects;

public record SkillGroupData(
    Locale locale,
    String name
) {

  public SkillGroupData {
    Objects.requireNonNull(locale);
    Objects.requireNonNull(name);

    if (name.isBlank()) {
      throw new IllegalArgumentException("Skill group name can not be blank");
    }
  }
}
