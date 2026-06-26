package dev.tsumakov.domain.model.core;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dev.tsumakov.domain.exception.DomainValidationException;
import org.junit.jupiter.api.Test;

public class RoleTest {

  @Test
  public void shouldCreateRoleWhenAllFieldsAreValid() {
    assertThatCode(() -> new Role(1, "ADMIN"))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldThrowExceptionWhenNameIsNull() {
    assertThatThrownBy(() -> new Role(1, null))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenNameIsBlank() {
    assertThatThrownBy(() -> new Role(1, "  "))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldNotThrowExceptionWhenIdIsNull() {
    assertThatCode(() -> new Role(null, "USER"))
        .doesNotThrowAnyException();
  }
}
