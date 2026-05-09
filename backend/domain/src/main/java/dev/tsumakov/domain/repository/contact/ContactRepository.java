package dev.tsumakov.domain.repository.contact;

import dev.tsumakov.domain.model.contact.Contact;
import dev.tsumakov.domain.model.contact.ContactItem;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

public interface ContactRepository {
  Optional<Contact> findById(UUID id);

  List<ContactItem> findByUserId(UUID userId);

  List<ContactItem> findLocalizedByUserId(UUID userId, Locale locale);

  void save(Contact contact);

  void delete(UUID id);
}
