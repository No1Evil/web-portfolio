package dev.tsumakov.domain.core.skill.factory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dev.tsumakov.domain.shared.exception.DomainValidationException;
import org.junit.jupiter.api.Test;

public class SkillFactoryTest {

  private final SkillFactory factory = new SkillFactory();

  @Test
  public void shouldCreateNewSkillWithDefaultValues() {
    var skill = factory.createNew(2, "Java", "icon.svg");

    assertThat(skill.id()).isNull();
    assertThat(skill.categoryId()).isEqualTo(2);
    assertThat(skill.name()).isEqualTo("Java");
    assertThat(skill.iconUrl()).isEqualTo("icon.svg");
    assertThat(skill.version()).isEqualTo(1L);
    assertThat(skill.createdAt()).isNotNull();
    assertThat(skill.updatedAt()).isNotNull();
  }

  @Test
  public void shouldAllowNullIconUrl() {
    var skill = factory.createNew(2, "Java", null);
    assertThat(skill.iconUrl()).isNull();
  }

  @Test
  public void shouldAllowNullCategoryId() {
    var skill = factory.createNew(null, "Java", "icon.svg");
    assertThat(skill.categoryId()).isNull();
  }

  @Test
  public void shouldThrowExceptionWhenNameIsBlank() {
    assertThatThrownBy(() -> factory.createNew(2, "  ", "icon.svg"))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenNameIsNull() {
    assertThatThrownBy(() -> factory.createNew(2, null, "icon.svg"))
        .isInstanceOf(DomainValidationException.class);
  }
}
