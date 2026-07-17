package dev.tsumakov.domain.portfolio.model;

import dev.tsumakov.domain.shared.util.DomainObjects;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public record UserProject(
    Integer id,
    UUID userId,
    Map<String, String> title,
    Map<String, String> description,
    Set<Skill> skills,
    Boolean isFeatured,
    String projectUrl,
    String previewImageUrl
) {

  public UserProject {
    DomainObjects.requireNonNull(userId, "User ID must not be null");
    DomainObjects.requireNonNull(title, "Title map must not be null");
    DomainObjects.requireNonNull(description, "Description map must not be null");
    DomainObjects.requireNonNull(skills, "Skills set should not be null");
    isFeatured = Objects.requireNonNullElse(isFeatured, Boolean.FALSE);
  }

}
