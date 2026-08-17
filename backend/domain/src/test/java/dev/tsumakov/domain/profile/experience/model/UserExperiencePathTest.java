package dev.tsumakov.domain.profile.experience.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dev.tsumakov.domain.shared.exception.DomainValidationException;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class UserExperiencePathTest {

  private static final UUID ID = UUID.randomUUID();
  private static final OffsetDateTime CREATED_AT = OffsetDateTime.parse("2024-01-01T10:00:00Z");
  private static final OffsetDateTime UPDATED_AT = OffsetDateTime.parse("2024-06-01T10:00:00Z");
  private static final OffsetDateTime START = OffsetDateTime.parse("2020-01-01T10:00:00Z");
  private static final OffsetDateTime END = OffsetDateTime.parse("2024-01-01T10:00:00Z");

  private UserExperiencePath validExperience() {
    return new UserExperiencePath(ID, "Software Engineer", "Acme Corp", "Berlin",
        Map.of("en", "Built stuff"), START, END, false, CREATED_AT, UPDATED_AT, 2L);
  }

  @Test
  public void shouldCreateExperienceWhenAllFieldsAreValid() {
    assertThatCode(() -> validExperience()).doesNotThrowAnyException();
  }

  @Test
  public void shouldThrowExceptionWhenIdIsNull() {
    assertThatThrownBy(
        () -> new UserExperiencePath(null, "Software Engineer", "Acme Corp", "Berlin",
            Map.of("en", "Built stuff"), START, END, false, CREATED_AT, UPDATED_AT, 2L))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("UserExperiencePath ID must not be null");
  }

  @Test
  public void shouldThrowExceptionWhenTitleIsNull() {
    assertThatThrownBy(
        () -> new UserExperiencePath(ID, null, "Acme Corp", "Berlin", Map.of("en", "Built stuff"),
            START, END, false, CREATED_AT, UPDATED_AT, 2L))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("title can not be null or blank");
  }

  @Test
  public void shouldThrowExceptionWhenTitleIsBlank() {
    assertThatThrownBy(
        () -> new UserExperiencePath(ID, "  ", "Acme Corp", "Berlin", Map.of("en", "Built stuff"),
            START, END, false, CREATED_AT, UPDATED_AT, 2L))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenCompanyNameIsNull() {
    assertThatThrownBy(
        () -> new UserExperiencePath(ID, "Software Engineer", null, "Berlin",
            Map.of("en", "Built stuff"), START, END, false, CREATED_AT, UPDATED_AT, 2L))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("companyName can not be null or blank");
  }

  @Test
  public void shouldThrowExceptionWhenCompanyNameIsBlank() {
    assertThatThrownBy(
        () -> new UserExperiencePath(ID, "Software Engineer", "  ", "Berlin",
            Map.of("en", "Built stuff"), START, END, false, CREATED_AT, UPDATED_AT, 2L))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenLocationIsNull() {
    assertThatThrownBy(
        () -> new UserExperiencePath(ID, "Software Engineer", "Acme Corp", null,
            Map.of("en", "Built stuff"), START, END, false, CREATED_AT, UPDATED_AT, 2L))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("location can not be null or blank");
  }

  @Test
  public void shouldThrowExceptionWhenLocationIsBlank() {
    assertThatThrownBy(
        () -> new UserExperiencePath(ID, "Software Engineer", "Acme Corp", "  ",
            Map.of("en", "Built stuff"), START, END, false, CREATED_AT, UPDATED_AT, 2L))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenDescriptionIsNull() {
    assertThatThrownBy(
        () -> new UserExperiencePath(ID, "Software Engineer", "Acme Corp", "Berlin", null, START,
            END, false, CREATED_AT, UPDATED_AT, 2L))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenDescriptionHasNoEngEntry() {
    assertThatThrownBy(
        () -> new UserExperiencePath(ID, "Software Engineer", "Acme Corp", "Berlin",
            Map.of("ru", "Делал вещи"), START, END, false, CREATED_AT, UPDATED_AT, 2L))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("Map should have \"en\" entry");
  }

  @Test
  public void shouldThrowExceptionWhenEngDescriptionIsBlank() {
    assertThatThrownBy(
        () -> new UserExperiencePath(ID, "Software Engineer", "Acme Corp", "Berlin",
            Map.of("en", " "), START, END, false, CREATED_AT, UPDATED_AT, 2L))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("English description entry (\"en\") must not be blank");
  }

  @Test
  public void shouldAllowNullStartDateWithPresentEndDate() {
    assertThatCode(
        () -> new UserExperiencePath(ID, "Software Engineer", "Acme Corp", "Berlin",
            Map.of("en", "Built stuff"), null, END, true, CREATED_AT, UPDATED_AT, 2L))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldAllowNullEndDateWhenPresentIsTrue() {
    assertThatCode(
        () -> new UserExperiencePath(ID, "Software Engineer", "Acme Corp", "Berlin",
            Map.of("en", "Built stuff"), START, null, true, CREATED_AT, UPDATED_AT, 2L))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldAllowBothDatesNull() {
    assertThatCode(
        () -> new UserExperiencePath(ID, "Software Engineer", "Acme Corp", "Berlin",
            Map.of("en", "Built stuff"), null, null, true, CREATED_AT, UPDATED_AT, 2L))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldThrowExceptionWhenStartDateIsAfterEndDate() {
    assertThatThrownBy(
        () -> new UserExperiencePath(ID, "Software Engineer", "Acme Corp", "Berlin",
            Map.of("en", "Built stuff"), END, START, false, CREATED_AT, UPDATED_AT, 2L))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("Start date can not be after end date");
  }

  @Test
  public void shouldAllowEqualDates() {
    assertThatCode(
        () -> new UserExperiencePath(ID, "Software Engineer", "Acme Corp", "Berlin",
            Map.of("en", "Built stuff"), START, START, false, CREATED_AT, UPDATED_AT, 2L))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldThrowExceptionWhenPresentIsNull() {
    assertThatThrownBy(
        () -> new UserExperiencePath(ID, "Software Engineer", "Acme Corp", "Berlin",
            Map.of("en", "Built stuff"), START, END, null, CREATED_AT, UPDATED_AT, 2L))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenCreatedAtIsNull() {
    assertThatThrownBy(
        () -> new UserExperiencePath(ID, "Software Engineer", "Acme Corp", "Berlin",
            Map.of("en", "Built stuff"), START, END, false, null, UPDATED_AT, 2L))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenUpdatedAtIsNull() {
    assertThatThrownBy(
        () -> new UserExperiencePath(ID, "Software Engineer", "Acme Corp", "Berlin",
            Map.of("en", "Built stuff"), START, END, false, CREATED_AT, null, 2L))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldDefensivelyCopyDescription() {
    Map<String, String> description = new HashMap<>();
    description.put("en", "Built stuff");
    var experience = new UserExperiencePath(ID, "Software Engineer", "Acme Corp", "Berlin",
        description, START, END, false, CREATED_AT, UPDATED_AT, 2L);

    description.put("en", "Mutated");
    description.put("ru", "Новое");

    assertThat(experience.description()).containsOnlyKeys("en");
    assertThat(experience.description().get("en")).isEqualTo("Built stuff");
  }

  @Test
  public void shouldUpdateTitlePreservingOtherFields() {
    var experience = validExperience().updateTitle("Senior Engineer");
    assertThat(experience.title()).isEqualTo("Senior Engineer");
    assertThat(experience.companyName()).isEqualTo("Acme Corp");
    assertThat(experience.location()).isEqualTo("Berlin");
    assertThat(experience.version()).isEqualTo(2L);
  }

  @Test
  public void shouldRejectBlankTitleOnUpdate() {
    assertThatThrownBy(() -> validExperience().updateTitle(null))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldUpdateCompanyNamePreservingOtherFields() {
    var experience = validExperience().updateCompanyName("Globex");
    assertThat(experience.companyName()).isEqualTo("Globex");
    assertThat(experience.title()).isEqualTo("Software Engineer");
  }

  @Test
  public void shouldRejectBlankCompanyNameOnUpdate() {
    assertThatThrownBy(() -> validExperience().updateCompanyName("   "))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldUpdateLocationPreservingOtherFields() {
    var experience = validExperience().updateLocation("Munich");
    assertThat(experience.location()).isEqualTo("Munich");
    assertThat(experience.companyName()).isEqualTo("Acme Corp");
  }

  @Test
  public void shouldUpdateDescriptionPreservingOtherFields() {
    var experience = validExperience().updateDescription(Map.of("en", "Led a team"));
    assertThat(experience.description()).containsEntry("en", "Led a team");
    assertThat(experience.title()).isEqualTo("Software Engineer");
  }

  @Test
  public void shouldRejectDescriptionWithoutEngOnUpdate() {
    assertThatThrownBy(() -> validExperience().updateDescription(Map.of("de", "Team geführt")))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldUpdateStartDatePreservingOtherFields() {
    var newStart = OffsetDateTime.parse("2018-01-01T10:00:00Z");
    var experience = validExperience().updateStartDate(newStart);
    assertThat(experience.startDate()).isEqualTo(newStart);
    assertThat(experience.endDate()).isEqualTo(END);
  }

  @Test
  public void shouldUpdateEndDatePreservingOtherFields() {
    var newEnd = OffsetDateTime.parse("2025-01-01T10:00:00Z");
    var experience = validExperience().updateEndDate(newEnd);
    assertThat(experience.endDate()).isEqualTo(newEnd);
    assertThat(experience.startDate()).isEqualTo(START);
  }

  @Test
  public void shouldRejectInvalidDateRangeOnUpdate() {
    var later = OffsetDateTime.parse("2030-01-01T10:00:00Z");
    assertThatThrownBy(() -> validExperience().updateStartDate(later))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldUpdatePresentPreservingOtherFields() {
    var experience = validExperience().updatePresent(true);
    assertThat(experience.present()).isTrue();
    assertThat(experience.title()).isEqualTo("Software Engineer");
  }

  @Test
  public void shouldRejectNullPresentOnUpdate() {
    assertThatThrownBy(() -> validExperience().updatePresent(null))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldIncrementVersionAndRefreshUpdatedAt() {
    var experience = validExperience().withIncrementedVersion();
    assertThat(experience.version()).isEqualTo(3L);
    assertThat(experience.updatedAt()).isAfterOrEqualTo(UPDATED_AT);
    assertThat(experience.createdAt()).isEqualTo(CREATED_AT);
    assertThat(experience.title()).isEqualTo("Software Engineer");
  }
}
