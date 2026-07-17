package dev.tsumakov.domain.portfolio.model;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dev.tsumakov.domain.shared.exception.DomainValidationException;
import org.junit.jupiter.api.Test;

public class SkillCategoryTest {

  @Test
  public void shouldCreateSkillCategoryWhenAllFieldsAreValid() {
    assertThatCode(() -> new SkillCategory(1, "Backend", "icon.png"))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldThrowExceptionWhenNameIsNull() {
    assertThatThrownBy(() -> new SkillCategory(1, null, "icon.png"))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenNameIsBlank() {
    assertThatThrownBy(() -> new SkillCategory(1, "  ", "icon.png"))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldNotThrowExceptionWhenIdIsNull() {
    assertThatCode(() -> new SkillCategory(null, "Backend", "icon.png"))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldNotThrowExceptionWhenIconUrlIsNull() {
    assertThatCode(() -> new SkillCategory(1, "Backend", null))
        .doesNotThrowAnyException();
  }
}
