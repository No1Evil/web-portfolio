package dev.tsumakov.domain.profile.education.factory;

import dev.tsumakov.domain.profile.education.model.UserEducationPath;
import java.time.OffsetDateTime;
import java.util.Map;

public class UserEducationPathFactory {

  public UserEducationPath createNew(
      String title, String location, Map<String, String> description,
      OffsetDateTime startDate, OffsetDateTime endDate, Boolean present
  ) {
    return new UserEducationPath(null, title, location,
        description, startDate, endDate, present, OffsetDateTime.now(), OffsetDateTime.now(), 1L);
  }
}
