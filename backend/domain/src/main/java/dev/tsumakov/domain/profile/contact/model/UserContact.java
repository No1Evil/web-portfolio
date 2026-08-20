package dev.tsumakov.domain.profile.contact.model;

import dev.tsumakov.domain.shared.util.DomainObjects;
import java.time.OffsetDateTime;

public record UserContact(
    Integer id,
    String title,
    String redirectUrl,
    String iconUrl,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt,
    long version
) {

  public UserContact {
    DomainObjects.requireNotBlank(title, "title");
  }

  public UserContact updateTitle(String title) {
    return new UserContact(id, title, redirectUrl, iconUrl, createdAt, updatedAt, version);
  }

  public UserContact updateRedirectUrl(String redirectUrl) {
    return new UserContact(id, title, redirectUrl, iconUrl, createdAt, updatedAt, version);
  }

  public UserContact updateIconUrl(String iconUrl) {
    return new UserContact(id, title, redirectUrl, iconUrl, createdAt, updatedAt, version);
  }

}
