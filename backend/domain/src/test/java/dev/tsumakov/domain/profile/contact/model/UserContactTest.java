package dev.tsumakov.domain.profile.contact.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dev.tsumakov.domain.shared.exception.DomainValidationException;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;

public class UserContactTest {

  private static final OffsetDateTime CREATED_AT = OffsetDateTime.parse("2024-01-01T10:00:00Z");
  private static final OffsetDateTime UPDATED_AT = OffsetDateTime.parse("2024-06-01T10:00:00Z");

  private UserContact validContact() {
    return new UserContact(1, "Email", "mailto:test@example.com", "icon.svg", CREATED_AT,
        UPDATED_AT, 4L);
  }

  @Test
  public void shouldCreateUserContactWhenAllFieldsAreValid() {
    assertThatCode(() -> validContact()).doesNotThrowAnyException();
  }

  @Test
  public void shouldThrowExceptionWhenTitleIsNull() {
    assertThatThrownBy(
        () -> new UserContact(1, null, "mailto:test@example.com", "icon.svg", CREATED_AT,
            UPDATED_AT, 4L))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("title can not be null or blank");
  }

  @Test
  public void shouldThrowExceptionWhenTitleIsBlank() {
    assertThatThrownBy(
        () -> new UserContact(1, "  ", "mailto:test@example.com", "icon.svg", CREATED_AT,
            UPDATED_AT, 4L))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenTitleIsEmpty() {
    assertThatThrownBy(
        () -> new UserContact(1, "", "mailto:test@example.com", "icon.svg", CREATED_AT,
            UPDATED_AT, 4L))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldAllowNullTimestamps() {
    assertThatCode(
        () -> new UserContact(1, "Email", "mailto:test@example.com", "icon.svg", null, null, 4L))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldAllowNullId() {
    assertThatCode(
        () -> new UserContact(null, "Email", "mailto:test@example.com", "icon.svg", CREATED_AT,
            UPDATED_AT, 4L))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldAllowNullRedirectUrl() {
    assertThatCode(
        () -> new UserContact(1, "Email", null, "icon.svg", CREATED_AT, UPDATED_AT, 4L))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldAllowNullIconUrl() {
    assertThatCode(
        () -> new UserContact(1, "Email", "mailto:test@example.com", null, CREATED_AT, UPDATED_AT,
            4L))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldUpdateTitlePreservingOtherFields() {
    var contact = validContact().updateTitle("Phone");
    assertThat(contact.title()).isEqualTo("Phone");
    assertThat(contact.id()).isEqualTo(1);
    assertThat(contact.redirectUrl()).isEqualTo("mailto:test@example.com");
    assertThat(contact.iconUrl()).isEqualTo("icon.svg");
    assertThat(contact.createdAt()).isEqualTo(CREATED_AT);
    assertThat(contact.updatedAt()).isEqualTo(UPDATED_AT);
    assertThat(contact.version()).isEqualTo(4L);
  }

  @Test
  public void shouldRejectBlankTitleOnUpdate() {
    assertThatThrownBy(() -> validContact().updateTitle("   "))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldUpdateRedirectUrlPreservingOtherFields() {
    var contact = validContact().updateRedirectUrl("https://example.com");
    assertThat(contact.redirectUrl()).isEqualTo("https://example.com");
    assertThat(contact.title()).isEqualTo("Email");
  }

  @Test
  public void shouldUpdateIconUrlPreservingOtherFields() {
    var contact = validContact().updateIconUrl("new-icon.svg");
    assertThat(contact.iconUrl()).isEqualTo("new-icon.svg");
    assertThat(contact.version()).isEqualTo(4L);
  }
}
