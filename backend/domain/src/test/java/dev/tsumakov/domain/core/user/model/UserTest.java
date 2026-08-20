package dev.tsumakov.domain.core.user.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dev.tsumakov.domain.core.user.exception.UserIdValidationException;
import dev.tsumakov.domain.shared.exception.DomainValidationException;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;

public class UserTest {

  private static final OffsetDateTime CREATED_AT = OffsetDateTime.parse("2024-01-01T10:00:00Z");
  private static final OffsetDateTime UPDATED_AT = OffsetDateTime.parse("2024-06-01T10:00:00Z");

  private User validUser() {
    return new User(1, "admin", "$2a$10$hash", CREATED_AT, UPDATED_AT, 5L);
  }

  @Test
  public void shouldCreateUserWhenAllFieldsAreValid() {
    assertThatCode(() -> validUser()).doesNotThrowAnyException();
  }

  @Test
  public void shouldThrowExceptionWhenIdIsNull() {
    assertThatThrownBy(() -> new User(null, "admin", "$2a$10$hash", CREATED_AT, UPDATED_AT, 5L))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("User ID must not be null");
  }

  @Test
  public void shouldThrowExceptionWhenIdIsNotOne() {
    assertThatThrownBy(() -> new User(2, "admin", "$2a$10$hash", CREATED_AT, UPDATED_AT, 5L))
        .isInstanceOf(UserIdValidationException.class)
        .hasMessage("User without id '1' is prohibited");
  }

  @Test
  public void shouldThrowExceptionWhenIdIsZero() {
    assertThatThrownBy(() -> new User(0, "admin", "$2a$10$hash", CREATED_AT, UPDATED_AT, 5L))
        .isInstanceOf(UserIdValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenUsernameIsNull() {
    assertThatThrownBy(() -> new User(1, null, "$2a$10$hash", CREATED_AT, UPDATED_AT, 5L))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("username can not be null or blank");
  }

  @Test
  public void shouldThrowExceptionWhenUsernameIsBlank() {
    assertThatThrownBy(() -> new User(1, "   ", "$2a$10$hash", CREATED_AT, UPDATED_AT, 5L))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenPasswordHashIsNull() {
    assertThatThrownBy(() -> new User(1, "admin", null, CREATED_AT, UPDATED_AT, 5L))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("passwordHash can not be null or blank");
  }

  @Test
  public void shouldThrowExceptionWhenPasswordHashIsBlank() {
    assertThatThrownBy(() -> new User(1, "admin", " ", CREATED_AT, UPDATED_AT, 5L))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldUpdatePasswordPreservingOtherFields() {
    var user = validUser().updatePassword("$2a$10$new-hash");
    assertThat(user.passwordHash()).isEqualTo("$2a$10$new-hash");
    assertThat(user.id()).isEqualTo(1);
    assertThat(user.username()).isEqualTo("admin");
    assertThat(user.createdAt()).isEqualTo(CREATED_AT);
    assertThat(user.updatedAt()).isEqualTo(UPDATED_AT);
    assertThat(user.version()).isEqualTo(5L);
  }

  @Test
  public void shouldRejectBlankPasswordOnUpdate() {
    assertThatThrownBy(() -> validUser().updatePassword(null))
        .isInstanceOf(DomainValidationException.class);
  }
}
