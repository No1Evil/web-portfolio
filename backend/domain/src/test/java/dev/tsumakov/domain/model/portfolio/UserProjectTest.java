package dev.tsumakov.domain.model.portfolio;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dev.tsumakov.domain.exception.DomainValidationException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class UserProjectTest {

  private static final UUID VALID_UUID = UUID.randomUUID();

  @Test
  public void shouldCreateUserProjectWhenAllFieldsAreValid() {
    assertThatCode(
        () -> new UserProject(1, VALID_UUID, Map.of("en", "title"), Map.of("en", "desc"),
            Set.of(new Skill(1, 1, "Java")), true, "url", "img"))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldThrowExceptionWhenUserIdIsNull() {
    assertThatThrownBy(
        () -> new UserProject(1, null, Map.of("en", "title"), Map.of("en", "desc"),
            Set.of(new Skill(1, 1, "Java")), true, "url", "img"))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenTitleIsNull() {
    assertThatThrownBy(
        () -> new UserProject(1, VALID_UUID, null, Map.of("en", "desc"),
            Set.of(new Skill(1, 1, "Java")), true, "url", "img"))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenDescriptionIsNull() {
    assertThatThrownBy(
        () -> new UserProject(1, VALID_UUID, Map.of("en", "title"), null,
            Set.of(new Skill(1, 1, "Java")), true, "url", "img"))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenSkillsAreNull() {
    assertThatThrownBy(
        () -> new UserProject(1, VALID_UUID, Map.of("en", "title"), Map.of("en", "desc"), null, true,
            "url", "img"))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldDefaultIsFeaturedToFalseWhenNull() {
    var project = new UserProject(1, VALID_UUID, Map.of("en", "title"), Map.of("en", "desc"),
        Set.of(new Skill(1, 1, "Java")), null, "url", "img");
    assertThat(project.isFeatured()).isFalse();
  }

  @Test
  public void shouldNotThrowExceptionWhenIdIsNull() {
    assertThatCode(
        () -> new UserProject(null, VALID_UUID, Map.of("en", "title"), Map.of("en", "desc"),
            Set.of(new Skill(1, 1, "Java")), true, "url", "img"))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldNotThrowExceptionWhenProjectUrlIsNull() {
    assertThatCode(
        () -> new UserProject(1, VALID_UUID, Map.of("en", "title"), Map.of("en", "desc"),
            Set.of(new Skill(1, 1, "Java")), true, null, "img"))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldNotThrowExceptionWhenPreviewImageUrlIsNull() {
    assertThatCode(
        () -> new UserProject(1, VALID_UUID, Map.of("en", "title"), Map.of("en", "desc"),
            Set.of(new Skill(1, 1, "Java")), true, "url", null))
        .doesNotThrowAnyException();
  }
}
