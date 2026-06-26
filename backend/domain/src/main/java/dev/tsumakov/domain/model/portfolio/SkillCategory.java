package dev.tsumakov.domain.model.portfolio;

import dev.tsumakov.domain.util.DomainObjects;

public record SkillCategory(
    Integer id,
    String name,
    String iconUrl
) {

  public SkillCategory {
    DomainObjects.requireNotBlank(name, "name");
  }

}
