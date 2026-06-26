package dev.tsumakov.domain.model.portfolio;

import dev.tsumakov.domain.util.DomainObjects;

public record Skill(
    Integer id,
    Integer categoryId,
    String name
) {

  public Skill {
    DomainObjects.requireNotBlank(name, "name");
  }

}
