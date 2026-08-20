package dev.tsumakov.domain.profile.summary.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dev.tsumakov.domain.core.user.exception.UserIdValidationException;
import dev.tsumakov.domain.shared.exception.DomainValidationException;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class UserSummaryTest {

  private static final OffsetDateTime CREATED_AT = OffsetDateTime.parse("2024-01-01T10:00:00Z");
  private static final OffsetDateTime UPDATED_AT = OffsetDateTime.parse("2024-06-01T10:00:00Z");

  private UserSummary validSummary() {
    return new UserSummary(1, "John", "Doe", "Senior Software Engineer",
        Map.of("en", "Experienced developer"), "hero.png", CREATED_AT, UPDATED_AT, 7L);
  }

  @Test
  public void shouldCreateUserSummaryWhenAllFieldsAreValid() {
    assertThatCode(() -> validSummary()).doesNotThrowAnyException();
  }

  @Test
  public void shouldNotThrowExceptionWhenIdIsNull() {
    assertThatNoException().isThrownBy(
            () -> new UserSummary(null, "John", "Doe", "Engineer", Map.of("en", "Bio"), "hero.png",
                CREATED_AT, UPDATED_AT, 7L));
  }

  @Test
  public void shouldThrowExceptionWhenIdIsNotOne() {
    assertThatThrownBy(
        () -> new UserSummary(3, "John", "Doe", "Engineer", Map.of("en", "Bio"), "hero.png",
            CREATED_AT, UPDATED_AT, 7L))
        .isInstanceOf(UserIdValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenFirstNameIsNull() {
    assertThatThrownBy(
        () -> new UserSummary(1, null, "Doe", "Engineer", Map.of("en", "Bio"), "hero.png",
            CREATED_AT, UPDATED_AT, 7L))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("firstName can not be null or blank");
  }

  @Test
  public void shouldThrowExceptionWhenFirstNameIsBlank() {
    assertThatThrownBy(
        () -> new UserSummary(1, "  ", "Doe", "Engineer", Map.of("en", "Bio"), "hero.png",
            CREATED_AT, UPDATED_AT, 7L))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenLastNameIsNull() {
    assertThatThrownBy(
        () -> new UserSummary(1, "John", null, "Engineer", Map.of("en", "Bio"), "hero.png",
            CREATED_AT, UPDATED_AT, 7L))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("lastName can not be null or blank");
  }

  @Test
  public void shouldThrowExceptionWhenLastNameIsBlank() {
    assertThatThrownBy(
        () -> new UserSummary(1, "John", " ", "Engineer", Map.of("en", "Bio"), "hero.png",
            CREATED_AT, UPDATED_AT, 7L))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenProficiencyIsNull() {
    assertThatThrownBy(
        () -> new UserSummary(1, "John", "Doe", null, Map.of("en", "Bio"), "hero.png", CREATED_AT,
            UPDATED_AT, 7L))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("proficiency can not be null or blank");
  }

  @Test
  public void shouldThrowExceptionWhenProficiencyIsBlank() {
    assertThatThrownBy(
        () -> new UserSummary(1, "John", "Doe", "  ", Map.of("en", "Bio"), "hero.png", CREATED_AT,
            UPDATED_AT, 7L))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenDescriptionIsNull() {
    assertThatThrownBy(
        () -> new UserSummary(1, "John", "Doe", "Engineer", null, "hero.png", CREATED_AT,
            UPDATED_AT, 7L))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("Description map must not be null");
  }

  @Test
  public void shouldThrowExceptionWhenDescriptionHasNoEngEntry() {
    assertThatThrownBy(
        () -> new UserSummary(1, "John", "Doe", "Engineer", Map.of("et", "Arenguarendaja"),
            "hero.png", CREATED_AT, UPDATED_AT, 7L))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("Map should have \"en\" entry");
  }

  @Test
  public void shouldThrowExceptionWhenEngDescriptionIsNull() {
    Map<String, String> description = new HashMap<>();
    description.put("en", null);
    assertThatThrownBy(
        () -> new UserSummary(1, "John", "Doe", "Engineer", description, "hero.png", CREATED_AT,
            UPDATED_AT, 7L))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("English description entry (\"en\") must not be blank");
  }

  @Test
  public void shouldThrowExceptionWhenEngDescriptionIsBlank() {
    assertThatThrownBy(
        () -> new UserSummary(1, "John", "Doe", "Engineer", Map.of("en", "   "), "hero.png",
            CREATED_AT, UPDATED_AT, 7L))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("English description entry (\"en\") must not be blank");
  }

  @Test
  public void shouldAllowNullHeroImageUrl() {
    assertThatCode(
        () -> new UserSummary(1, "John", "Doe", "Engineer", Map.of("en", "Bio"), null, CREATED_AT,
            UPDATED_AT, 7L))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldAllowNullTimestamps() {
    assertThatCode(
        () -> new UserSummary(1, "John", "Doe", "Engineer", Map.of("en", "Bio"), "hero.png", null,
            null, 7L))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldDefensivelyCopyDescription() {
    Map<String, String> description = new HashMap<>();
    description.put("en", "Bio");
    var summary = new UserSummary(1, "John", "Doe", "Engineer", description, "hero.png",
        CREATED_AT, UPDATED_AT, 7L);

    description.put("en", "Mutated");
    description.put("ru", "Новое");

    assertThat(summary.description()).containsOnlyKeys("en");
    assertThat(summary.description().get("en")).isEqualTo("Bio");
  }

  @Test
  public void shouldUpdateFirstNamePreservingOtherFields() {
    var summary = validSummary().updateFirstName("Jane");
    assertThat(summary.firstName()).isEqualTo("Jane");
    assertThat(summary.lastName()).isEqualTo("Doe");
    assertThat(summary.proficiency()).isEqualTo("Senior Software Engineer");
    assertThat(summary.heroImageUrl()).isEqualTo("hero.png");
    assertThat(summary.version()).isEqualTo(7L);
  }

  @Test
  public void shouldRejectBlankFirstNameOnUpdate() {
    assertThatThrownBy(() -> validSummary().updateFirstName("  "))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldUpdateLastNamePreservingOtherFields() {
    var summary = validSummary().updateLastName("Smith");
    assertThat(summary.lastName()).isEqualTo("Smith");
    assertThat(summary.firstName()).isEqualTo("John");
  }

  @Test
  public void shouldUpdateProficiencyPreservingOtherFields() {
    var summary = validSummary().updateProficiency("Lead Engineer");
    assertThat(summary.proficiency()).isEqualTo("Lead Engineer");
    assertThat(summary.firstName()).isEqualTo("John");
  }

  @Test
  public void shouldUpdateDescriptionPreservingOtherFields() {
    var summary = validSummary().updateDescription(Map.of("en", "New bio"));
    assertThat(summary.description()).containsEntry("en", "New bio");
    assertThat(summary.firstName()).isEqualTo("John");
  }

  @Test
  public void shouldRejectDescriptionWithoutEngOnUpdate() {
    assertThatThrownBy(() -> validSummary().updateDescription(Map.of("ru", "Новое био")))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldUpdateHeroImageUrlPreservingOtherFields() {
    var summary = validSummary().updateHeroImageUrl("new-hero.png");
    assertThat(summary.heroImageUrl()).isEqualTo("new-hero.png");
    assertThat(summary.version()).isEqualTo(7L);
  }
}
