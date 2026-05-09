package dev.tsumakov.domain.model.contact;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents User's contacts.
 * @param id contact id
 * @param userId contact owner id
 * @param items list of contacts
 */
public record Contact(
    UUID id,
    UUID userId,
    List<ContactItem> items
) {

  public Contact {
    Objects.requireNonNull(id);
    Objects.requireNonNull(userId);
    items = List.copyOf(items);
  }
}
