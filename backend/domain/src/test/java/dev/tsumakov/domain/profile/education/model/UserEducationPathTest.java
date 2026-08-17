package dev.tsumakov.domain.profile.education.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dev.tsumakov.domain.shared.exception.DomainValidationException;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class UserEducationPathTest {

  private static final UUID ID = UUID.randomUUID();
  private static final OffsetDateTime CREATED_AT = OffsetDateTime.parse("2024-01-01T10:00:00Z");
  private static final OffsetDateTime UPDATED_AT = OffsetDateTime.parse("2024-06-01T10:00:00Z");
  private static final OffsetDateTime START = OffsetDateTime.parse("2020-01-01T10:00:00Z");
  private static final OffsetDateTime END = OffsetDateTime.parse("2024-01-01T10:00:00Z");

  private UserEducationPath validEducation() {
    return new UserEducationPath(ID, "MIT", "Boston", Map.of("en", "BSc Computer Science"),
        START, END, false, CREATED_AT, UPDATED_AT, 2L);
  }

  @Test
  public void shouldCreateEducationWhenAllFieldsAreValid() {
    assertThatCode(() -> validEducation()).doesNotThrowAnyException();
  }

  @Test
  public void shouldThrowExceptionWhenIdIsNull() {
    assertThatThrownBy(
        () -> new UserEducationPath(null, "MIT", "Boston", Map.of("en", "BSc"), START, END, false,
            CREATED_AT, UPDATED_AT, 2L))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("UserEducationPath ID must not be null");
  }

  @Test
  public void shouldThrowExceptionWhenTitleIsNull() {
    assertThatThrownBy(
        () -> new UserEducationPath(ID, null, "Boston", Map.of("en", "BSc"), START, END, false,
            CREATED_AT, UPDATED_AT, 2L))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("title can not be null or blank");
  }

  @Test
  public void shouldThrowExceptionWhenTitleIsBlank() {
    assertThatThrownBy(
        () -> new UserEducationPath(ID, "  ", "Boston", Map.of("en", "BSc"), START, END, false,
            CREATED_AT, UPDATED_AT, 2L))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenLocationIsNull() {
    assertThatThrownBy(
        () -> new UserEducationPath(ID, "MIT", null, Map.of("en", "BSc"), START, END, false,
            CREATED_AT, UPDATED_AT, 2L))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("location can not be null or blank");
  }

  @Test
  public void shouldThrowExceptionWhenLocationIsBlank() {
    assertThatThrownBy(
        () -> new UserEducationPath(ID, "MIT", "  ", Map.of("en", "BSc"), START, END, false,
            CREATED_AT, UPDATED_AT, 2L))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenDescriptionIsNull() {
    assertThatThrownBy(
        () -> new UserEducationPath(ID, "MIT", "Boston", null, START, END, false, CREATED_AT,
            UPDATED_AT, 2L))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenDescriptionHasNoEngEntry() {
    assertThatThrownBy(
        () -> new UserEducationPath(ID, "MIT", "Boston", Map.of("ru", "Бакалавр"), START, END,
            false, CREATED_AT, UPDATED_AT, 2L))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("Map should have \"en\" entry");
  }

  @Test
  public void shouldThrowExceptionWhenEngDescriptionIsNull() {
    Map<String, String> description = new HashMap<>();
    description.put("en", null);
    assertThatThrownBy(
        () -> new UserEducationPath(ID, "MIT", "Boston", description, START, END, false, CREATED_AT,
            UPDATED_AT, 2L))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("English description entry (\"en\") must not be blank");
  }

  @Test
  public void shouldThrowExceptionWhenEngDescriptionIsBlank() {
    assertThatThrownBy(
        () -> new UserEducationPath(ID, "MIT", "Boston", Map.of("en", "  "), START, END, false,
            CREATED_AT, UPDATED_AT, 2L))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("English description entry (\"en\") must not be blank");
  }

  @Test
  public void shouldAllowNullStartDateWhenEndDateIsNull() {
    assertThatCode(
        () -> new UserEducationPath(ID, "MIT", "Boston", Map.of("en", "BSc"), null, null, true,
            CREATED_AT, UPDATED_AT, 2L))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldAllowNullStartDateWithPresentEndDate() {
    assertThatCode(
        () -> new UserEducationPath(ID, "MIT", "Boston", Map.of("en", "BSc"), null, END, true,
            CREATED_AT, UPDATED_AT, 2L))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldAllowNullEndDateWhenPresentIsTrue() {
    assertThatCode(
        () -> new UserEducationPath(ID, "MIT", "Boston", Map.of("en", "BSc"), START, null, true,
            CREATED_AT, UPDATED_AT, 2L))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldThrowExceptionWhenStartDateIsAfterEndDate() {
    assertThatThrownBy(
        () -> new UserEducationPath(ID, "MIT", "Boston", Map.of("en", "BSc"), END, START, false,
            CREATED_AT, UPDATED_AT, 2L))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("Start date can not be after end date");
  }

  @Test
  public void shouldAllowEqualDates() {
    assertThatCode(
        () -> new UserEducationPath(ID, "MIT", "Boston", Map.of("en", "BSc"), START, START, false,
            CREATED_AT, UPDATED_AT, 2L))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldThrowExceptionWhenPresentIsNull() {
    assertThatThrownBy(
        () -> new UserEducationPath(ID, "MIT", "Boston", Map.of("en", "BSc"), START, END, null,
            CREATED_AT, UPDATED_AT, 2L))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("Field can not be null");
  }

  @Test
  public void shouldThrowExceptionWhenCreatedAtIsNull() {
    assertThatThrownBy(
        () -> new UserEducationPath(ID, "MIT", "Boston", Map.of("en", "BSc"), START, END, false,
            null, UPDATED_AT, 2L))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenUpdatedAtIsNull() {
    assertThatThrownBy(
        () -> new UserEducationPath(ID, "MIT", "Boston", Map.of("en", "BSc"), START, END, false,
            CREATED_AT, null, 2L))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldDefensivelyCopyDescription() {
    Map<String, String> description = new HashMap<>();
    description.put("en", "BSc");
    var education = new UserEducationPath(ID, "MIT", "Boston", description, START, END, false,
        CREATED_AT, UPDATED_AT, 2L);

    description.put("en", "Mutated");
    description.put("ru", "Новый");

    assertThat(education.description()).containsOnlyKeys("en");
    assertThat(education.description().get("en")).isEqualTo("BSc");
  }

  @Test
  public void shouldUpdateTitlePreservingOtherFields() {
    var education = validEducation().updateTitle("Harvard");
    assertThat(education.title()).isEqualTo("Harvard");
    assertThat(education.location()).isEqualTo("Boston");
    assertThat(education.description()).containsEntry("en", "BSc Computer Science");
    assertThat(education.startDate()).isEqualTo(START);
    assertThat(education.endDate()).isEqualTo(END);
    assertThat(education.present()).isFalse();
    assertThat(education.version()).isEqualTo(2L);
  }

  @Test
  public void shouldRejectBlankTitleOnUpdate() {
    assertThatThrownBy(() -> validEducation().updateTitle(" "))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldUpdateLocationPreservingOtherFields() {
    var education = validEducation().updateLocation("Cambridge");
    assertThat(education.location()).isEqualTo("Cambridge");
    assertThat(education.title()).isEqualTo("MIT");
  }

  @Test
  public void shouldUpdateDescriptionPreservingOtherFields() {
    var education = validEducation().updateDescription(Map.of("en", "MSc"));
    assertThat(education.description()).containsEntry("en", "MSc");
    assertThat(education.title()).isEqualTo("MIT");
  }

  @Test
  public void shouldRejectDescriptionWithoutEngOnUpdate() {
    assertThatThrownBy(() -> validEducation().updateDescription(Map.of("ru", "Магистр")))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldUpdateStartDatePreservingOtherFields() {
    var newStart = OffsetDateTime.parse("2018-01-01T10:00:00Z");
    var education = validEducation().updateStartDate(newStart);
    assertThat(education.startDate()).isEqualTo(newStart);
    assertThat(education.endDate()).isEqualTo(END);
  }

  @Test
  public void shouldUpdateEndDatePreservingOtherFields() {
    var newEnd = OffsetDateTime.parse("2025-01-01T10:00:00Z");
    var education = validEducation().updateEndDate(newEnd);
    assertThat(education.endDate()).isEqualTo(newEnd);
    assertThat(education.startDate()).isEqualTo(START);
  }

  @Test
  public void shouldRejectInvalidDateRangeOnUpdate() {
    var later = OffsetDateTime.parse("2030-01-01T10:00:00Z");
    assertThatThrownBy(() -> validEducation().updateStartDate(later))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldUpdatePresentPreservingOtherFields() {
    var education = validEducation().updatePresent(true);
    assertThat(education.present()).isTrue();
    assertThat(education.title()).isEqualTo("MIT");
  }

  @Test
  public void shouldRejectNullPresentOnUpdate() {
    assertThatThrownBy(() -> validEducation().updatePresent(null))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldIncrementVersionAndRefreshUpdatedAt() {
    var education = validEducation().withIncrementedVersion();
    assertThat(education.version()).isEqualTo(3L);
    assertThat(education.updatedAt()).isAfterOrEqualTo(UPDATED_AT);
    assertThat(education.createdAt()).isEqualTo(CREATED_AT);
    assertThat(education.title()).isEqualTo("MIT");
  }
}
