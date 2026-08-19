package dev.tsumakov.domain.shared.util;

import dev.tsumakov.domain.shared.exception.DomainValidationException;
import java.time.OffsetDateTime;
import java.util.Map;

public final class DomainObjects {

  private DomainObjects() {
    throw new AssertionError("Nope.");
  }

  @SuppressWarnings("UnusedReturnValue")
  public static <T> T requireNonNull(T obj) {
    return requireNonNull(obj, "Field can not be null");
  }

  @SuppressWarnings("UnusedReturnValue")
  public static <T> T requireNonNull(T obj, String exceptionMessage) {
    if (obj == null) {
      throw new DomainValidationException(exceptionMessage);
    }
    return obj;
  }

  @SuppressWarnings("UnusedReturnValue")
  public static String requireNotBlank(String str, String fieldName) {
    if (str == null || str.isBlank()) {
      throw new DomainValidationException(fieldName + " can not be null or blank");
    }
    return str;
  }

  public static void requireValidDates(OffsetDateTime start, OffsetDateTime end){
    requireNonNull(start, " start date can not be null");
    requireNonNull(end, " end date can not be null");

    if (start.isAfter(end)) {
      throw new DomainValidationException("Start date can not be after end date");
    }
  }

  public static void requireMapHasEngEntry(Map<String, String> map) {
    if (map == null || !map.containsKey("en")) {
      throw new DomainValidationException("Map should have \"en\" entry");
    }

    String enValue = map.get("en");
    if (enValue == null || enValue.isBlank()) {
      throw new DomainValidationException("English description entry (\"en\") must not be blank");
    }
  }
}
