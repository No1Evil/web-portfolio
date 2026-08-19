package dev.tsumakov.domain.shared.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dev.tsumakov.domain.shared.exception.DomainValidationException;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class DomainObjectsTest {

  private static final OffsetDateTime START = OffsetDateTime.parse("2020-01-01T10:00:00Z");
  private static final OffsetDateTime END = OffsetDateTime.parse("2024-01-01T10:00:00Z");

  @Test
  public void shouldReturnNonNullObject() {
    var obj = new Object();
    assertThat(DomainObjects.requireNonNull(obj)).isSameAs(obj);
  }

  @Test
  public void shouldThrowExceptionWithDefaultMessageWhenNull() {
    assertThatThrownBy(() -> DomainObjects.requireNonNull(null))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("Field can not be null");
  }

  @Test
  public void shouldThrowExceptionWithCustomMessageWhenNull() {
    assertThatThrownBy(() -> DomainObjects.requireNonNull(null, "User ID must not be null"))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("User ID must not be null");
  }

  @Test
  public void shouldReturnObjectFromRequireNonNullWithCustomMessage() {
    var obj = new Object();
    assertThat(DomainObjects.requireNonNull(obj, "custom")).isSameAs(obj);
  }

  @Test
  public void shouldReturnNonBlankString() {
    assertThat(DomainObjects.requireNotBlank("  Java  ", "name")).isEqualTo("  Java  ");
  }

  @Test
  public void shouldThrowExceptionWhenStringIsNull() {
    assertThatThrownBy(() -> DomainObjects.requireNotBlank(null, "name"))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("name can not be null or blank");
  }

  @Test
  public void shouldThrowExceptionWhenStringIsEmpty() {
    assertThatThrownBy(() -> DomainObjects.requireNotBlank("", "name"))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenStringIsWhitespaceOnly() {
    assertThatThrownBy(() -> DomainObjects.requireNotBlank(" \t\n", "name"))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldAcceptValidDateRange() {
    assertThatCode(() -> DomainObjects.requireValidDates(START, END))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldAcceptEqualDates() {
    assertThatCode(() -> DomainObjects.requireValidDates(START, START))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldThrowExceptionWhenStartIsAfterEnd() {
    assertThatThrownBy(() -> DomainObjects.requireValidDates(END, START))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("Start date can not be after end date");
  }

  @Test
  public void shouldThrowExceptionWhenStartIsNull() {
    assertThatThrownBy(() -> DomainObjects.requireValidDates(null, END))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage(" start date can not be null");
  }

  @Test
  public void shouldThrowExceptionWhenEndIsNull() {
    assertThatThrownBy(() -> DomainObjects.requireValidDates(START, null))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage(" end date can not be null");
  }

  @Test
  public void shouldThrowExceptionWhenBothDatesAreNull() {
    assertThatThrownBy(() -> DomainObjects.requireValidDates(null, null))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldAcceptMapWithNonBlankEngEntry() {
    assertThatCode(() -> DomainObjects.requireMapHasEngEntry(Map.of("en", "Hello", "ru", "Привет")))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldThrowExceptionWhenMapIsNull() {
    assertThatThrownBy(() -> DomainObjects.requireMapHasEngEntry(null))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("Map should have \"en\" entry");
  }

  @Test
  public void shouldThrowExceptionWhenMapIsEmpty() {
    assertThatThrownBy(() -> DomainObjects.requireMapHasEngEntry(Map.of()))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenMapHasNoEngEntry() {
    assertThatThrownBy(() -> DomainObjects.requireMapHasEngEntry(Map.of("ru", "Привет")))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("Map should have \"en\" entry");
  }

  @Test
  public void shouldThrowExceptionWhenEngEntryIsNull() {
    Map<String, String> map = new HashMap<>();
    map.put("en", null);
    assertThatThrownBy(() -> DomainObjects.requireMapHasEngEntry(map))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("English description entry (\"en\") must not be blank");
  }

  @Test
  public void shouldThrowExceptionWhenEngEntryIsBlank() {
    assertThatThrownBy(() -> DomainObjects.requireMapHasEngEntry(Map.of("en", "  ")))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("English description entry (\"en\") must not be blank");
  }
}
