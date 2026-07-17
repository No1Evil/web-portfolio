package dev.tsumakov.domain.profile.model;

import dev.tsumakov.domain.shared.util.DomainObjects;
import java.util.Map;
import java.util.UUID;

public record UserContact(
    Integer id,
    UUID userId,
    Map<String, String> title,
    Map<String, String> subtitle,
    String redirectUrl,
    String iconUrl
) {

  public UserContact {
    DomainObjects.requireNonNull(userId, "User ID must not be null");
    DomainObjects.requireNonNull(title, "Title map must not be null");
    DomainObjects.requireNonNull(subtitle, "Subtitle map must not be null");
  }

}
