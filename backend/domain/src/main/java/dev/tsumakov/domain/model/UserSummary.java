package dev.tsumakov.domain.model;

import java.util.Objects;

public record UserSummary(
    String firstName,
    String surname,
    AssetPath avatar
) {

  public UserSummary {
    Objects.requireNonNull(firstName);
    Objects.requireNonNull(surname);

    if (firstName.isBlank()) {
      throw new IllegalArgumentException("First name shouldn't be blank");
    }

    if (surname.isBlank()) {
      throw new IllegalArgumentException("Surname shouldn't be blank");
    }

    if (firstName.length() < 3) {
      throw new IllegalArgumentException("First name length should be longer than 2");
    }
  }
}
