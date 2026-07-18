package dev.tsumakov.domain.shared.util;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dev.tsumakov.domain.shared.exception.DomainValidationException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Test;

public class DomainObjectsTest {

  private record TestObject(Object obj, String str) {

    public TestObject {
      DomainObjects.requireNonNull(obj);
      DomainObjects.requireNotBlank(str, "str");
    }
  }

  @Test
  public void shouldCreateObjectWhenAllFieldsAreValid() {
    assertThatCode(() -> new TestObject(new Object(), "test"))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldThrowExceptionOnInvalidField() {
    assertThatThrownBy(() -> new TestObject(null, "test"))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionOnBlankField() {
    assertThatThrownBy(() -> new TestObject(new Object(), " "))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionOnNullStrField() {
    assertThatThrownBy(() -> new TestObject(new Object(), null))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowErrorOnInvalidDates() {
    OffsetDateTime startDate = OffsetDateTime.of(2025, 2, 2, 0, 0, 0, 0, ZoneOffset.MIN);
    OffsetDateTime endDate = OffsetDateTime.of(2024, 2, 2, 0, 0, 0, 0, ZoneOffset.MIN);
    assertThatThrownBy(() -> DomainObjects.requireValidDates(startDate, endDate))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldReturnObjectFromRequireNonNullWithCustomMessage() {
    var obj = new Object();
    assertThatCode(() -> DomainObjects.requireNonNull(obj, "custom"))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldThrowExceptionWithCustomMessage() {
    assertThatThrownBy(() -> DomainObjects.requireNonNull(null, "Custom message"))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("Custom message");
  }

  @Test
  public void shouldThrowExceptionWithDefaultMessage() {
    assertThatThrownBy(() -> DomainObjects.requireNonNull(null))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("Field can not be null");
  }

  @Test
  public void shouldNotThrowWhenValidDatesWithNullCheck() {
    OffsetDateTime start = OffsetDateTime.of(2020, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
    OffsetDateTime end = OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
    assertThatCode(() -> DomainObjects.requireValidDates(start, end))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldThrowWhenStartIsNullWithNullCheck() {
    OffsetDateTime end = OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
    assertThatThrownBy(() -> DomainObjects.requireValidDates(null, end))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowWhenEndIsNullWithNullCheck() {
    OffsetDateTime start = OffsetDateTime.of(2020, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
    assertThatThrownBy(() -> DomainObjects.requireValidDates(start, null))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowWhenBothDatesAreNullWithNullCheck() {
    assertThatThrownBy(() -> DomainObjects.requireValidDates(null, null))
        .isInstanceOf(DomainValidationException.class);
  }
}
