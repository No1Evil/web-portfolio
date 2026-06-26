package dev.tsumakov.domain.model.profile;

import dev.tsumakov.domain.util.DomainObjects;
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

}
