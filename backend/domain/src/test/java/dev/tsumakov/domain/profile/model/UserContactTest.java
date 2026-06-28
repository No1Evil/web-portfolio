package dev.tsumakov.domain.profile.model;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dev.tsumakov.domain.shared.exception.DomainValidationException;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class UserContactTest {

  private static final UUID VALID_UUID = UUID.randomUUID();

  @Test
  public void shouldCreateUserContactWhenAllFieldsAreValid() {
    assertThatCode(
        () -> new UserContact(1, VALID_UUID, Map.of("en", "Email"), Map.of("en", "Work"),
            "mailto:test@example.com", "icon.svg"))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldThrowExceptionWhenUserIdIsNull() {
    assertThatThrownBy(
        () -> new UserContact(1, null, Map.of("en", "Email"), Map.of("en", "Work"),
            "mailto:test@example.com", "icon.svg"))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenTitleIsNull() {
    assertThatThrownBy(
        () -> new UserContact(1, VALID_UUID, null, Map.of("en", "Work"),
            "mailto:test@example.com", "icon.svg"))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenSubtitleIsNull() {
    assertThatThrownBy(
        () -> new UserContact(1, VALID_UUID, Map.of("en", "Email"), null,
            "mailto:test@example.com", "icon.svg"))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldNotThrowExceptionWhenIdIsNull() {
    assertThatCode(
        () -> new UserContact(null, VALID_UUID, Map.of("en", "Email"), Map.of("en", "Work"),
            "mailto:test@example.com", "icon.svg"))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldNotThrowExceptionWhenRedirectUrlIsNull() {
    assertThatCode(
        () -> new UserContact(1, VALID_UUID, Map.of("en", "Email"), Map.of("en", "Work"), null,
            "icon.svg"))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldNotThrowExceptionWhenIconUrlIsNull() {
    assertThatCode(
        () -> new UserContact(1, VALID_UUID, Map.of("en", "Email"), Map.of("en", "Work"),
            "mailto:test@example.com", null))
        .doesNotThrowAnyException();
  }
}
