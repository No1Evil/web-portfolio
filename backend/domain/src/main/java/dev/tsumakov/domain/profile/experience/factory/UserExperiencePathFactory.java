package dev.tsumakov.domain.profile.experience.factory;

import dev.tsumakov.domain.profile.experience.model.UserExperiencePath;
import java.time.OffsetDateTime;
import java.util.Map;

public class UserExperiencePathFactory {

  public UserExperiencePath createNew(
      String title, String companyName, String location, Map<String, String> description,
      OffsetDateTime startDate, OffsetDateTime endDate, Boolean present
  ) {
    return new UserExperiencePath(null, title, companyName, location,
        description, startDate, endDate, present, OffsetDateTime.now(), OffsetDateTime.now(), 1L);
  }

}
