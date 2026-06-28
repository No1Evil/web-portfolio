package dev.tsumakov.domain.portfolio.model;

import dev.tsumakov.domain.shared.util.DomainObjects;

public record SkillCategory(
    Integer id,
    String name,
    String iconUrl
) {

  public SkillCategory {
    DomainObjects.requireNotBlank(name, "name");
  }

}
