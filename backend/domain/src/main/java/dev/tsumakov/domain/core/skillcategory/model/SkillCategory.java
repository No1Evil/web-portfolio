package dev.tsumakov.domain.core.skillcategory.model;

import dev.tsumakov.domain.shared.util.DomainObjects;
import java.time.OffsetDateTime;

public record SkillCategory(
    Integer id,
    String name,
    String iconUrl,
    long version,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt
) {

  public SkillCategory {
    DomainObjects.requireNotBlank(name, "name");
  }

  public SkillCategory updateIconUrl(String iconUrl) {
    return new SkillCategory(id, name, iconUrl, version, createdAt, updatedAt);
  }

  public SkillCategory updateName(String name) {
    return new SkillCategory(id, name, iconUrl, version, createdAt, updatedAt);
  }

}
