package dev.tsumakov.domain.model.profile;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dev.tsumakov.domain.exception.DomainValidationException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class ExperienceTest {

  private static final UUID VALID_UUID = UUID.randomUUID();
  private static final OffsetDateTime EARLIER = OffsetDateTime.of(2020, 1, 1, 0, 0, 0, 0,
      ZoneOffset.UTC);
  private static final OffsetDateTime LATER = OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0,
      ZoneOffset.UTC);

  @Test
  public void shouldCreateExperienceWhenAllFieldsAreValid() {
    assertThatCode(
        () -> new Experience(1, VALID_UUID, "Acme", "Engineer", Map.of("en", "dev"), EARLIER,
            LATER))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldThrowExceptionWhenUserIdIsNull() {
    assertThatThrownBy(
        () -> new Experience(1, null, "Acme", "Engineer", Map.of("en", "dev"), EARLIER, LATER))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenCompanyIsBlank() {
    assertThatThrownBy(
        () -> new Experience(1, VALID_UUID, null, "Engineer", Map.of("en", "dev"), EARLIER, LATER))
        .isInstanceOf(DomainValidationException.class);
    assertThatThrownBy(
        () -> new Experience(1, VALID_UUID, "  ", "Engineer", Map.of("en", "dev"), EARLIER, LATER))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenPositionIsBlank() {
    assertThatThrownBy(
        () -> new Experience(1, VALID_UUID, "Acme", null, Map.of("en", "dev"), EARLIER, LATER))
        .isInstanceOf(DomainValidationException.class);
    assertThatThrownBy(
        () -> new Experience(1, VALID_UUID, "Acme", "  ", Map.of("en", "dev"), EARLIER, LATER))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenDescriptionIsNull() {
    assertThatThrownBy(
        () -> new Experience(1, VALID_UUID, "Acme", "Engineer", null, EARLIER, LATER))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenStartDateIsAfterEndDate() {
    assertThatThrownBy(
        () -> new Experience(1, VALID_UUID, "Acme", "Engineer", Map.of("en", "dev"), LATER,
            EARLIER))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldNotThrowExceptionWhenDatesAreEqual() {
    assertThatCode(
        () -> new Experience(1, VALID_UUID, "Acme", "Engineer", Map.of("en", "dev"), EARLIER,
            EARLIER))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldNotThrowExceptionWhenIdIsNull() {
    assertThatCode(
        () -> new Experience(null, VALID_UUID, "Acme", "Engineer", Map.of("en", "dev"), EARLIER,
            LATER))
        .doesNotThrowAnyException();
  }
}
