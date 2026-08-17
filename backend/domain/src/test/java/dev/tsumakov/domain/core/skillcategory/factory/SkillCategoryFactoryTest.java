package dev.tsumakov.domain.core.skillcategory.factory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dev.tsumakov.domain.shared.exception.DomainValidationException;
import org.junit.jupiter.api.Test;

public class SkillCategoryFactoryTest {

  private final SkillCategoryFactory factory = new SkillCategoryFactory();

  @Test
  public void shouldCreateNewCategoryWithDefaultValues() {
    var category = factory.createNew("Backend", "icon.svg");

    assertThat(category.id()).isNull();
    assertThat(category.name()).isEqualTo("Backend");
    assertThat(category.iconUrl()).isEqualTo("icon.svg");
    assertThat(category.version()).isEqualTo(1L);
    assertThat(category.createdAt()).isNotNull();
    assertThat(category.updatedAt()).isNotNull();
  }

  @Test
  public void shouldAllowNullIconUrl() {
    var category = factory.createNew("Backend", null);
    assertThat(category.iconUrl()).isNull();
  }

  @Test
  public void shouldThrowExceptionWhenNameIsNull() {
    assertThatThrownBy(() -> factory.createNew(null, "icon.svg"))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenNameIsBlank() {
    assertThatThrownBy(() -> factory.createNew("  ", "icon.svg"))
        .isInstanceOf(DomainValidationException.class);
  }
}
