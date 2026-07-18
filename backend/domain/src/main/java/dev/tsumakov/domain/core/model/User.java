package dev.tsumakov.domain.core.model;

import dev.tsumakov.domain.portfolio.model.Skill;
import dev.tsumakov.domain.shared.util.DomainObjects;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

public record User(
    UUID id,
    String firstName,
    String lastName,
    String email,
    String avatarUrl,
    String password,
    Set<Role> roles,
    Set<Skill> skills,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt
) {

  public User {
    DomainObjects.requireNonNull(id, "User ID must not be null");
    DomainObjects.requireNonNull(id);
    DomainObjects.requireNotBlank(firstName, "firstName");
    DomainObjects.requireNotBlank(lastName, "lastName");
    DomainObjects.requireNotBlank(email, "email");
    DomainObjects.requireNotBlank(password, "password");
    DomainObjects.requireNonNull(roles, "Roles set must not be null");
    DomainObjects.requireNonNull(createdAt);
    DomainObjects.requireNonNull(updatedAt);
  }

  public boolean hasRole(String roleName) {
    return roles.stream()
        .anyMatch(role -> role.name().equalsIgnoreCase(roleName));
  }

}
