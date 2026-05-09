package dev.tsumakov.domain.model.contact;

import dev.tsumakov.domain.model.AssetPath;
import java.net.URI;
import java.util.List;
import java.util.Objects;

/**
 * Represents a contact item.
 * @param icon path to icon
 * @param link link
 * @param details details
 */
public record ContactItem(
    AssetPath icon,
    URI link,
    List<ContactData> details
) {

  public ContactItem {
    Objects.requireNonNull(icon);
    Objects.requireNonNull(link);
    details = List.copyOf(details);
  }

}