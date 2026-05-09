package dev.tsumakov.domain.model.biography;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * Represents user's biography.
 *
 * @param id     biography id
 * @param userId owner id
 * @param data   localized data
 */
public record Biography(
    UUID id,
    UUID userId,
    List<BiographyData> data
) {

  public Biography {
    Objects.requireNonNull(id);
    Objects.requireNonNull(userId);
    if (data == null || data.isEmpty()) {
      throw new IllegalArgumentException("Biography must have at least one paragraph");
    }

    long uniqueLocales = data.stream()
        .map(BiographyData::locale)
        .distinct()
        .count();

    if (uniqueLocales != data.size()) {
      throw new IllegalArgumentException("Duplicate locales found in biography data");
    }

    data = List.copyOf(data);
  }

  public Optional<BiographyData> getDescriptionByLocale(Locale locale) {
    return data.stream().filter(d -> d.locale().equals(locale)).findFirst();
  }

  public Biography(UUID userId, List<BiographyData> data) {
    this(UUID.randomUUID(), userId, List.copyOf(data));
  }
}
