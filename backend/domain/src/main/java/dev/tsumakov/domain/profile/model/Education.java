package dev.tsumakov.domain.profile.model;

import dev.tsumakov.domain.shared.util.DomainObjects;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

public record Education(
    Integer id,
    UUID userId,
    Map<String, String> institution,
    Map<String, String> degree,
    OffsetDateTime startDate,
    OffsetDateTime endDate
) {

  public Education {
    // id НЕ проверяем на null!
    DomainObjects.requireNonNull(userId, "User ID must not be null");
    DomainObjects.requireNonNull(institution, "Institution map must not be null");
    DomainObjects.requireNonNull(degree, "Degree map must not be null");
    DomainObjects.requireValidDates(startDate, endDate);
  }
}
