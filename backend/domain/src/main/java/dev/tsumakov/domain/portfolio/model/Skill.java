package dev.tsumakov.domain.portfolio.model;

import dev.tsumakov.domain.shared.util.DomainObjects;

public record Skill(
    Integer id,
    Integer categoryId,
    String name
) {

  public Skill {
    DomainObjects.requireNotBlank(name, "name");
  }

}
