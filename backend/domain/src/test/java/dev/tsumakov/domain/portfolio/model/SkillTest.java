package dev.tsumakov.domain.portfolio.model;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dev.tsumakov.domain.shared.exception.DomainValidationException;
import org.junit.jupiter.api.Test;

public class SkillTest {

  @Test
  public void shouldCreateSkillWhenAllFieldsAreValid() {
    assertThatCode(() -> new Skill(1, 1, "Java"))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldThrowExceptionWhenNameIsNull() {
    assertThatThrownBy(() -> new Skill(1, 1, null))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenNameIsBlank() {
    assertThatThrownBy(() -> new Skill(1, 1, "  "))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldNotThrowExceptionWhenIdIsNull() {
    assertThatCode(() -> new Skill(null, 1, "Java"))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldNotThrowExceptionWhenCategoryIdIsNull() {
    assertThatCode(() -> new Skill(1, null, "Java"))
        .doesNotThrowAnyException();
  }
}
