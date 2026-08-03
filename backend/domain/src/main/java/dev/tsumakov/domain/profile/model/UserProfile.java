package dev.tsumakov.domain.profile.model;

import dev.tsumakov.domain.shared.util.DomainObjects;
import java.util.Map;
import java.util.UUID;

public record UserProfile(
    UUID userId,
    Map<String, String> title,
    Map<String, String> description
) {

  public UserProfile {
    DomainObjects.requireNonNull(userId, "User ID must not be null");
  }

  public static UserProfile createNew(
      UUID userId,
      Map<String, String> title,
      Map<String, String> description
  ) {
    return new UserProfile(userId, title, description);
  }

  public static UserProfile empty(UUID userId) {
    return new UserProfile(userId, Map.of(), Map.of());
  }

}
