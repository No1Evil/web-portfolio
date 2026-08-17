package dev.tsumakov.domain.profile.contact.factory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dev.tsumakov.domain.shared.exception.DomainValidationException;
import org.junit.jupiter.api.Test;

public class UserContactFactoryTest {

  private final UserContactFactory factory = new UserContactFactory();

  @Test
  public void shouldCreateNewContactWithDefaultValues() {
    var contact = factory.createNew("Email", "mailto:test@example.com", "icon.svg");

    assertThat(contact.id()).isNull();
    assertThat(contact.title()).isEqualTo("Email");
    assertThat(contact.redirectUrl()).isEqualTo("mailto:test@example.com");
    assertThat(contact.iconUrl()).isEqualTo("icon.svg");
    assertThat(contact.version()).isEqualTo(1L);
    assertThat(contact.createdAt()).isNotNull();
    assertThat(contact.updatedAt()).isNotNull();
  }

  @Test
  public void shouldAllowNullRedirectUrl() {
    var contact = factory.createNew("Email", null, "icon.svg");
    assertThat(contact.redirectUrl()).isNull();
  }

  @Test
  public void shouldAllowNullIconUrl() {
    var contact = factory.createNew("Email", "mailto:test@example.com", null);
    assertThat(contact.iconUrl()).isNull();
  }

  @Test
  public void shouldThrowExceptionWhenTitleIsNull() {
    assertThatThrownBy(() -> factory.createNew(null, "mailto:test@example.com", "icon.svg"))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenTitleIsBlank() {
    assertThatThrownBy(() -> factory.createNew("  ", "mailto:test@example.com", "icon.svg"))
        .isInstanceOf(DomainValidationException.class);
  }
}
