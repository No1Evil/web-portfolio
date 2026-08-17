package dev.tsumakov.domain.profile.experience.factory;

import dev.tsumakov.domain.profile.experience.model.UserExperiencePath;
import dev.tsumakov.domain.shared.util.UuidGenerator;
import java.time.OffsetDateTime;
import java.util.Map;

public class UserExperiencePathFactory {

  private final UuidGenerator uuidGenerator;

  public UserExperiencePathFactory(UuidGenerator uuidGenerator) {
    this.uuidGenerator = uuidGenerator;
  }

  public UserExperiencePath createNew(
      String title, String companyName, String location, Map<String, String> description,
      OffsetDateTime startDate, OffsetDateTime endDate, Boolean present,
      OffsetDateTime createdAt, OffsetDateTime updatedAt
  ) {
    return new UserExperiencePath(uuidGenerator.generate(), title, companyName, location,
        description, startDate, endDate, present, createdAt, updatedAt, 1L);
  }

}
