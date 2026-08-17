package dev.tsumakov.domain.profile.education.factory;

import dev.tsumakov.domain.profile.education.model.UserEducationPath;
import dev.tsumakov.domain.shared.util.UuidGenerator;
import java.time.OffsetDateTime;
import java.util.Map;

public class UserEducationPathFactory {
  private final UuidGenerator uuidGenerator;

  public UserEducationPathFactory(UuidGenerator uuidGenerator) {
    this.uuidGenerator = uuidGenerator;
  }

  public UserEducationPath createNew(
      String title, String location, Map<String, String> description,
      OffsetDateTime startDate, OffsetDateTime endDate, Boolean present,
      OffsetDateTime createdAt, OffsetDateTime updatedAt
  ) {
    return new UserEducationPath(uuidGenerator.generate(), title, location,
        description, startDate, endDate, present, createdAt, updatedAt, 1L);
  }
}
