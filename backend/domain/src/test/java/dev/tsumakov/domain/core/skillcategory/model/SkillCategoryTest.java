package dev.tsumakov.domain.core.skillcategory.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dev.tsumakov.domain.shared.exception.DomainValidationException;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;

public class SkillCategoryTest {

  private static final OffsetDateTime CREATED_AT = OffsetDateTime.parse("2024-01-01T10:00:00Z");
  private static final OffsetDateTime UPDATED_AT = OffsetDateTime.parse("2024-06-01T10:00:00Z");

  private SkillCategory validCategory() {
    return new SkillCategory(1, "Backend", "icon.svg", 2L, CREATED_AT, UPDATED_AT);
  }

  @Test
  public void shouldCreateSkillCategoryWhenAllFieldsAreValid() {
    assertThatCode(() -> validCategory()).doesNotThrowAnyException();
  }

  @Test
  public void shouldThrowExceptionWhenNameIsNull() {
    assertThatThrownBy(() -> new SkillCategory(1, null, "icon.svg", 2L, CREATED_AT, UPDATED_AT))
        .isInstanceOf(DomainValidationException.class)
        .hasMessage("name can not be null or blank");
  }

  @Test
  public void shouldThrowExceptionWhenNameIsBlank() {
    assertThatThrownBy(() -> new SkillCategory(1, " \t", "icon.svg", 2L, CREATED_AT, UPDATED_AT))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenNameIsEmpty() {
    assertThatThrownBy(() -> new SkillCategory(1, "", "icon.svg", 2L, CREATED_AT, UPDATED_AT))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldAllowNullId() {
    assertThatCode(() -> new SkillCategory(null, "Backend", "icon.svg", 2L, CREATED_AT, UPDATED_AT))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldAllowNullIconUrl() {
    assertThatCode(() -> new SkillCategory(1, "Backend", null, 2L, CREATED_AT, UPDATED_AT))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldAllowNullTimestamps() {
    assertThatCode(() -> new SkillCategory(1, "Backend", "icon.svg", 2L, null, null))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldUpdateNamePreservingOtherFields() {
    var category = validCategory().updateName("Frontend");
    assertThat(category.id()).isEqualTo(1);
    assertThat(category.name()).isEqualTo("Frontend");
    assertThat(category.iconUrl()).isEqualTo("icon.svg");
    assertThat(category.version()).isEqualTo(2L);
    assertThat(category.createdAt()).isEqualTo(CREATED_AT);
    assertThat(category.updatedAt()).isEqualTo(UPDATED_AT);
  }

  @Test
  public void shouldRejectBlankNameOnUpdate() {
    assertThatThrownBy(() -> validCategory().updateName(null))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldUpdateIconUrlPreservingOtherFields() {
    var category = validCategory().updateIconUrl("new-icon.svg");
    assertThat(category.iconUrl()).isEqualTo("new-icon.svg");
    assertThat(category.name()).isEqualTo("Backend");
    assertThat(category.version()).isEqualTo(2L);
  }

  @Test
  public void shouldIncrementVersionAndRefreshUpdatedAt() {
    var category = validCategory().withIncrementedVersion();
    assertThat(category.version()).isEqualTo(3L);
    assertThat(category.updatedAt()).isAfterOrEqualTo(UPDATED_AT);
    assertThat(category.createdAt()).isEqualTo(CREATED_AT);
    assertThat(category.name()).isEqualTo("Backend");
  }
}
