package dev.tsumakov.domain.model.project;

import java.util.Locale;
import java.util.Objects;

public record ProjectData(
    Locale locale,
    String title,
    String description
) {

  public ProjectData {
    Objects.requireNonNull(locale);
  }
}
