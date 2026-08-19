package dev.tsumakov.domain.profile.summary.model;

import dev.tsumakov.domain.core.user.exception.UserIdValidationException;
import dev.tsumakov.domain.shared.util.DomainObjects;
import java.time.OffsetDateTime;
import java.util.Map;

public record UserSummary(
    Integer id,
    String firstName,
    String lastName,
    String proficiency,
    Map<String, String> description,
    String heroImageUrl,
    OffsetDateTime createdAt,
    OffsetDateTime updatedAt,
    long version
) {

  public UserSummary {
    validateUserSummaryId(id);
    DomainObjects.requireNotBlank(firstName, "firstName");
    DomainObjects.requireNotBlank(lastName, "lastName");
    DomainObjects.requireNotBlank(proficiency, "proficiency");

    DomainObjects.requireNonNull(description, "Description map must not be null");
    DomainObjects.requireMapHasEngEntry(description);
    description = Map.copyOf(description);
  }

  public UserSummary updateFirstName(String firstName) {
    return new UserSummary(id, firstName, lastName, proficiency, description, heroImageUrl,
        createdAt, updatedAt, version);
  }

  public UserSummary updateLastName(String lastName) {
    return new UserSummary(id, firstName, lastName, proficiency, description, heroImageUrl,
        createdAt, updatedAt, version);
  }

  public UserSummary updateProficiency(String proficiency) {
    return new UserSummary(id, firstName, lastName, proficiency, description, heroImageUrl,
        createdAt, updatedAt, version);
  }

  public UserSummary updateDescription(Map<String, String> description) {
    return new UserSummary(id, firstName, lastName, proficiency, description, heroImageUrl,
        createdAt, updatedAt, version);
  }

  public UserSummary updateHeroImageUrl(String heroImageUrl) {
    return new UserSummary(id, firstName, lastName, proficiency, description, heroImageUrl,
        createdAt, updatedAt, version);
  }

  public UserSummary withIncrementedVersion() {
    return new UserSummary(id, firstName, lastName, proficiency, description, heroImageUrl,
        createdAt, OffsetDateTime.now(), version + 1);
  }

  private void validateUserSummaryId(Integer id) {
    DomainObjects.requireNonNull(id, "UserSummary ID must not be null");
    if (!id.equals(1)) {
      throw new UserIdValidationException("User without id '1' is prohibited");
    }
  }
}
