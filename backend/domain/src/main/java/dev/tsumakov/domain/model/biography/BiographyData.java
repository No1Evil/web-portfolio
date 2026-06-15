package dev.tsumakov.domain.model.biography;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

public record BiographyData(
    Locale locale,
    String name,
    String subtitle,
    List<String> paragraphs
) {

  public BiographyData {
    Objects.requireNonNull(locale);
    paragraphs = List.copyOf(paragraphs);
  }
}
