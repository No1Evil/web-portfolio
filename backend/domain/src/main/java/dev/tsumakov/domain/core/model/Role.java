package dev.tsumakov.domain.core.model;

import dev.tsumakov.domain.shared.util.DomainObjects;

public record Role(
    Integer id,
    String name
) {

  public static final String ADMIN_ROLE_NAME = "ADMIN";
  public static final String USER_ROLE_NAME = "USER";

  public Role {
    DomainObjects.requireNotBlank(name, "name");
  }

  public static Role createNew(String name) {
    return new Role(null, name);
  }

}
