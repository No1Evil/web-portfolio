package dev.tsumakov.domain.profile.summary.factory;

import dev.tsumakov.domain.profile.summary.model.UserSummary;
import java.time.OffsetDateTime;
import java.util.Map;

public class UserSummaryFactory {

  public UserSummary createNew(
      String firstName, String lastName, String proficiency,
      Map<String, String> description, String heroImageUrl
  ) {
    return new UserSummary(null, firstName, lastName, proficiency, description, heroImageUrl,
        OffsetDateTime.now(), OffsetDateTime.now(), 1L);
  }

}
