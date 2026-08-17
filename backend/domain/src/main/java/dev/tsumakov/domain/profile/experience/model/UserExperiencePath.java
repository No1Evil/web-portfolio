package dev.tsumakov.domain.profile.experience.model;

import dev.tsumakov.domain.shared.util.DomainObjects;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

public record UserExperiencePath(
    UUID id,
    String title,
    String companyName,
    String location,
    Map<String, String> description,
    OffsetDateTime startDate,
    OffsetDateTime endDate,
    Boolean present,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt,
    long version
) {

  public UserExperiencePath {
    DomainObjects.requireNonNull(id, "UserExperiencePath ID must not be null");
    DomainObjects.requireNotBlank(title, "title");
    DomainObjects.requireNotBlank(companyName, "companyName");
    DomainObjects.requireNotBlank(location, "location");

    DomainObjects.requireNonNull(description);
    DomainObjects.requireMapHasEngEntry(description);
    description = Map.copyOf(description);

    validateDates();
    DomainObjects.requireNonNull(present);
    DomainObjects.requireNonNull(createdAt);
    DomainObjects.requireNonNull(updatedAt);
  }

  public UserExperiencePath updateTitle(String title) {
    return new UserExperiencePath(id, title, companyName, location, description, startDate, endDate,
        present, createdAt, updatedAt, version);
  }

  public UserExperiencePath updateCompanyName(String companyName) {
    return new UserExperiencePath(id, title, companyName, location, description, startDate, endDate,
        present, createdAt, updatedAt, version);
  }

  public UserExperiencePath updateLocation(String location) {
    return new UserExperiencePath(id, title, companyName, location, description, startDate, endDate,
        present, createdAt, updatedAt, version);
  }

  public UserExperiencePath updateDescription(Map<String, String> description) {
    return new UserExperiencePath(id, title, companyName, location, description, startDate, endDate,
        present, createdAt, updatedAt, version);
  }

  public UserExperiencePath updateStartDate(OffsetDateTime startDate) {
    return new UserExperiencePath(id, title, companyName, location, description, startDate, endDate,
        present, createdAt, updatedAt, version);
  }

  public UserExperiencePath updateEndDate(OffsetDateTime endDate) {
    return new UserExperiencePath(id, title, companyName, location, description, startDate, endDate,
        present, createdAt, updatedAt, version);
  }

  public UserExperiencePath updatePresent(Boolean present) {
    return new UserExperiencePath(id, title, companyName, location, description, startDate, endDate,
        present, createdAt, updatedAt, version);
  }

  public UserExperiencePath withIncrementedVersion() {
    return new UserExperiencePath(id, title, companyName, location, description, startDate, endDate,
        present, createdAt, OffsetDateTime.now(), version + 1);
  }

  private void validateDates() {
    if (startDate != null && endDate != null) {
      DomainObjects.requireValidDates(startDate, endDate);
    }
  }

}
