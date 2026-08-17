package dev.tsumakov.domain.profile.contact.factory;

import dev.tsumakov.domain.profile.contact.model.UserContact;

public class UserContactFactory {

  public UserContact createNew(String title, String redirectUrl, String iconUrl) {
    return new UserContact(null, title, redirectUrl, iconUrl, 1L);
  }

}
