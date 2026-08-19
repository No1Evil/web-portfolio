package dev.tsumakov.domain.profile.contact.factory;

import dev.tsumakov.domain.profile.contact.model.UserContact;
import java.time.OffsetDateTime;

public class UserContactFactory {

  public UserContact createNew(String title, String redirectUrl, String iconUrl) {
    return new UserContact(null, title, redirectUrl, iconUrl, OffsetDateTime.now(), OffsetDateTime.now(), 1L);
  }

}
