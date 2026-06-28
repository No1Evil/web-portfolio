package dev.tsumakov.domain.profile.model;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dev.tsumakov.domain.shared.exception.DomainValidationException;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class UserProfileTest {

  private static final UUID VALID_UUID = UUID.randomUUID();

  @Test
  public void shouldCreateUserProfileWhenAllFieldsAreValid() {
    assertThatCode(
        () -> new UserProfile(VALID_UUID, Map.of("en", "Engineer"), Map.of("en", "Bio")))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldThrowExceptionWhenUserIdIsNull() {
    assertThatThrownBy(
        () -> new UserProfile(null, Map.of("en", "Engineer"), Map.of("en", "Bio")))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldNotThrowExceptionWhenTitleIsNull() {
    assertThatCode(
        () -> new UserProfile(VALID_UUID, null, Map.of("en", "Bio")))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldNotThrowExceptionWhenDescriptionIsNull() {
    assertThatCode(
        () -> new UserProfile(VALID_UUID, Map.of("en", "Engineer"), null))
        .doesNotThrowAnyException();
  }
}
