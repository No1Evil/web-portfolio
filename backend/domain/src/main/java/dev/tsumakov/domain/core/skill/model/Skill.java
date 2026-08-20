package dev.tsumakov.domain.core.skill.model;

import dev.tsumakov.domain.shared.util.DomainObjects;
import java.time.OffsetDateTime;

public record Skill(
    Integer id,
    Integer categoryId,
    String name,
    String iconUrl,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt,
    long version
) {

  public Skill {
    DomainObjects.requireNotBlank(name, "name");
    DomainObjects.requireNonNull(createdAt);
    DomainObjects.requireNonNull(updatedAt);
  }

  public Skill updateCategoryId(Integer categoryId) {
    return new Skill(id, categoryId, name, iconUrl, createdAt, updatedAt, version);
  }

  public Skill updateName(String name) {
    return new Skill(id, categoryId, name, iconUrl, createdAt, updatedAt, version);
  }

  public Skill updateIconUrl(String iconUrl) {
    return new Skill(id, categoryId, name, iconUrl, createdAt, updatedAt, version);
  }

}
