package dev.tsumakov.domain.model.profile;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dev.tsumakov.domain.exception.DomainValidationException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class EducationTest {

  private static final UUID VALID_UUID = UUID.randomUUID();
  private static final OffsetDateTime EARLIER = OffsetDateTime.of(2020, 1, 1, 0, 0, 0, 0,
      ZoneOffset.UTC);
  private static final OffsetDateTime LATER = OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0,
      ZoneOffset.UTC);

  @Test
  public void shouldCreateEducationWhenAllFieldsAreValid() {
    assertThatCode(
        () -> new Education(1, VALID_UUID, Map.of("en", "MIT"), Map.of("en", "BSc"), EARLIER,
            LATER))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldThrowExceptionWhenUserIdIsNull() {
    assertThatThrownBy(
        () -> new Education(1, null, Map.of("en", "MIT"), Map.of("en", "BSc"), EARLIER, LATER))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenInstitutionIsNull() {
    assertThatThrownBy(
        () -> new Education(1, VALID_UUID, null, Map.of("en", "BSc"), EARLIER, LATER))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenDegreeIsNull() {
    assertThatThrownBy(
        () -> new Education(1, VALID_UUID, Map.of("en", "MIT"), null, EARLIER, LATER))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenStartDateIsAfterEndDate() {
    assertThatThrownBy(
        () -> new Education(1, VALID_UUID, Map.of("en", "MIT"), Map.of("en", "BSc"), LATER,
            EARLIER))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenDatesAreEqual() {
    assertThatCode(
        () -> new Education(1, VALID_UUID, Map.of("en", "MIT"), Map.of("en", "BSc"), EARLIER,
            EARLIER))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldNotThrowExceptionWhenIdIsNull() {
    assertThatCode(
        () -> new Education(null, VALID_UUID, Map.of("en", "MIT"), Map.of("en", "BSc"), EARLIER,
            LATER))
        .doesNotThrowAnyException();
  }
}
