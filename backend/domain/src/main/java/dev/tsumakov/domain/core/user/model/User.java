package dev.tsumakov.domain.core.user.model;

import dev.tsumakov.domain.core.user.exception.UserIdValidationException;
import dev.tsumakov.domain.shared.util.DomainObjects;
import java.time.OffsetDateTime;

public record User(
    Integer id,
    String username,
    String passwordHash,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt,
    long version
) {

  public User {
    validateUserId();
    DomainObjects.requireNotBlank(username, "username");
    DomainObjects.requireNotBlank(passwordHash, "passwordHash");
  }

  public User updatePassword(String passwordHash) {
    return new User(id, username, passwordHash, createdAt, updatedAt, version);
  }

  public User withIncrementedVersion() {
    return new User(id, username, passwordHash, createdAt, OffsetDateTime.now(), version + 1);
  }

  private void validateUserId() {
    DomainObjects.requireNonNull(id, "User ID must not be null");
    if (!id.equals(1)) {
      throw new UserIdValidationException("User without id '1' is prohibited");
    }
  }
}
