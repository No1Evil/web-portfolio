package dev.tsumakov.domain.profile.model;

import dev.tsumakov.domain.shared.util.DomainObjects;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

public record Experience(
    Integer id,
    UUID userId,
    String company,
    String position,
    Map<String, String> description,
    OffsetDateTime startDate,
    OffsetDateTime endDate
) {
  public Experience {
    // id НЕ проверяем на null!
    DomainObjects.requireNonNull(userId, "User ID must not be null");
    DomainObjects.requireNotBlank(company, "Company");
    DomainObjects.requireNotBlank(position, "Position");
    DomainObjects.requireNonNull(description, "Description map must not be null");
    DomainObjects.requireValidDates(startDate, endDate);
  }

  public static Experience createNew(
      UUID userId,
      String company,
      String position,
      Map<String, String> description,
      OffsetDateTime startDate,
      OffsetDateTime endDate
  ) {
    return new Experience(null, userId, company, position, description, startDate, endDate);
  }
}
