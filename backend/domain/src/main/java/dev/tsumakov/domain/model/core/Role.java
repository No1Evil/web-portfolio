package dev.tsumakov.domain.model.core;

import dev.tsumakov.domain.util.DomainObjects;

public record Role(
    Integer id,
    String name
) {

  public static final String ADMIN_ROLE_NAME = "ADMIN";
  public static final String USER_ROLE_NAME = "USER";

  public Role {
    DomainObjects.requireNotBlank(name, "name");
  }

}
