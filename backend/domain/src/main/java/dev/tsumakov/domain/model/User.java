package dev.tsumakov.domain.model;

import java.util.Objects;
import java.util.UUID;

public record User(
    UUID id,
    UserSummary summary
) {

  public User {
    Objects.requireNonNull(id);
    Objects.requireNonNull(summary);
  }

  public User(UserSummary summary){
    this(UUID.randomUUID(), summary);
  }
}
