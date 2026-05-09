package dev.tsumakov.domain.model.contact;

import java.util.Locale;
import java.util.Objects;

public record ContactData(
    Locale locale,
    String title,
    String subtitle,
    String action
) {

  public ContactData {
    Objects.requireNonNull(locale);

    if (action.isBlank()) {
      throw new IllegalArgumentException();
    }
  }
}
