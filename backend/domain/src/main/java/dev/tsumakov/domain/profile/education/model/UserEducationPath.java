package dev.tsumakov.domain.profile.education.model;

import dev.tsumakov.domain.shared.util.DomainObjects;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

public record UserEducationPath(
    UUID id,
    String title,
    String location,
    Map<String, String> description,
    OffsetDateTime startDate,
    OffsetDateTime endDate,
    Boolean present,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt,
    long version
) {

  public UserEducationPath {
    DomainObjects.requireNotBlank(title, "title");
    DomainObjects.requireNotBlank(location, "location");

    DomainObjects.requireNonNull(description);
    DomainObjects.requireMapHasEngEntry(description);
    description = Map.copyOf(description);

    validateDates(startDate, endDate);
    DomainObjects.requireNonNull(present);
    DomainObjects.requireNonNull(createdAt);
    DomainObjects.requireNonNull(updatedAt);
  }

  public UserEducationPath updateTitle(String title) {
    return new UserEducationPath(id, title, location, description, startDate, endDate,
        present, createdAt, updatedAt, version);
  }

  public UserEducationPath updateLocation(String location) {
    return new UserEducationPath(id, title, location, description, startDate, endDate,
        present, createdAt, updatedAt, version);
  }

  public UserEducationPath updateDescription(Map<String, String> description) {
    return new UserEducationPath(id, title, location, description, startDate, endDate,
        present, createdAt, updatedAt, version);
  }

  public UserEducationPath updateStartDate(OffsetDateTime startDate) {
    return new UserEducationPath(id, title, location, description, startDate, endDate,
        present, createdAt, updatedAt, version);
  }

  public UserEducationPath updateEndDate(OffsetDateTime endDate) {
    return new UserEducationPath(id, title, location, description, startDate, endDate,
        present, createdAt, updatedAt, version);
  }

  public UserEducationPath updatePresent(Boolean present) {
    return new UserEducationPath(id, title, location, description, startDate, endDate,
        present, createdAt, updatedAt, version);
  }

  private void validateDates(OffsetDateTime startDate, OffsetDateTime endDate) {
    if (startDate != null && endDate != null) {
      DomainObjects.requireValidDates(startDate, endDate);
    }
  }

}
