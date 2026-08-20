package dev.tsumakov.domain.core.skill.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dev.tsumakov.domain.shared.exception.DomainValidationException;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;

public class SkillTest {

  private static final OffsetDateTime CREATED_AT = OffsetDateTime.parse("2024-01-01T10:00:00Z");
  private static final OffsetDateTime UPDATED_AT = OffsetDateTime.parse("2024-06-01T10:00:00Z");

  private Skill validSkill() {
    return new Skill(1, 2, "Java", "icon.svg", CREATED_AT, UPDATED_AT, 3L);
  }

  @Test
  public void shouldCreateSkillWhenAllFieldsAreValid() {
    assertThatCode(() -> validSkill()).doesNotThrowAnyException();
  }

  @Test
  public void shouldThrowExceptionWhenNameIsNull() {
    assertThatThrownBy(
        () -> new Skill(1, 2, null, "icon.svg", CREATED_AT, UPDATED_AT, 3L))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("name can not be null or blank");
  }

  @Test
  public void shouldThrowExceptionWhenNameIsBlank() {
    assertThatThrownBy(
        () -> new Skill(1, 2, "   ", "icon.svg", CREATED_AT, UPDATED_AT, 3L))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenNameIsEmpty() {
    assertThatThrownBy(
        () -> new Skill(1, 2, "", "icon.svg", CREATED_AT, UPDATED_AT, 3L))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenCreatedAtIsNull() {
    assertThatThrownBy(
        () -> new Skill(1, 2, "Java", "icon.svg", null, UPDATED_AT, 3L))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("Field can not be null");
  }

  @Test
  public void shouldThrowExceptionWhenUpdatedAtIsNull() {
    assertThatThrownBy(
        () -> new Skill(1, 2, "Java", "icon.svg", CREATED_AT, null, 3L))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldAllowNullId() {
    assertThatCode(() -> new Skill(null, 2, "Java", "icon.svg", CREATED_AT, UPDATED_AT, 3L))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldAllowNullCategoryId() {
    assertThatCode(() -> new Skill(1, null, "Java", "icon.svg", CREATED_AT, UPDATED_AT, 3L))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldAllowNullIconUrl() {
    assertThatCode(() -> new Skill(1, 2, "Java", null, CREATED_AT, UPDATED_AT, 3L))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldAllowNullIconUrlToBeUpdatedToNonNull() {
    var skill = new Skill(1, 2, "Java", null, CREATED_AT, UPDATED_AT, 3L)
        .updateIconUrl("new-icon.svg");
    assertThat(skill.iconUrl()).isEqualTo("new-icon.svg");
  }

  @Test
  public void shouldUpdateCategoryIdPreservingOtherFields() {
    var skill = validSkill().updateCategoryId(99);
    assertThat(skill.id()).isEqualTo(1);
    assertThat(skill.categoryId()).isEqualTo(99);
    assertThat(skill.name()).isEqualTo("Java");
    assertThat(skill.iconUrl()).isEqualTo("icon.svg");
    assertThat(skill.createdAt()).isEqualTo(CREATED_AT);
    assertThat(skill.updatedAt()).isEqualTo(UPDATED_AT);
    assertThat(skill.version()).isEqualTo(3L);
  }

  @Test
  public void shouldUpdateNamePreservingOtherFields() {
    var skill = validSkill().updateName("Kotlin");
    assertThat(skill.id()).isEqualTo(1);
    assertThat(skill.categoryId()).isEqualTo(2);
    assertThat(skill.name()).isEqualTo("Kotlin");
    assertThat(skill.version()).isEqualTo(3L);
  }

  @Test
  public void shouldUpdateIconUrlPreservingOtherFields() {
    var skill = validSkill().updateIconUrl("kotlin.svg");
    assertThat(skill.iconUrl()).isEqualTo("kotlin.svg");
    assertThat(skill.name()).isEqualTo("Java");
    assertThat(skill.version()).isEqualTo(3L);
  }

  @Test
  public void shouldRejectBlankNameOnUpdate() {
    assertThatThrownBy(() -> validSkill().updateName("  "))
        .isInstanceOf(DomainValidationException.class);
  }
}
