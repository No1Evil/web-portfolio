package dev.tsumakov.domain.profile.education.factory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dev.tsumakov.domain.shared.exception.DomainValidationException;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class UserEducationPathFactoryTest {

  private static final UUID GENERATED_ID = null;
  private static final OffsetDateTime START = OffsetDateTime.parse("2020-01-01T10:00:00Z");
  private static final OffsetDateTime END = OffsetDateTime.parse("2024-01-01T10:00:00Z");

  private final UserEducationPathFactory factory = new UserEducationPathFactory();

  @Test
  public void shouldCreateNewEducationWithDefaultsAndGeneratedId() {
    var education = factory.createNew("MIT", "Boston", Map.of("en", "BSc"), START, END, false);

    assertThat(education.id()).isEqualTo(GENERATED_ID);
    assertThat(education.title()).isEqualTo("MIT");
    assertThat(education.location()).isEqualTo("Boston");
    assertThat(education.description()).containsEntry("en", "BSc");
    assertThat(education.startDate()).isEqualTo(START);
    assertThat(education.endDate()).isEqualTo(END);
    assertThat(education.present()).isFalse();
    assertThat(education.version()).isEqualTo(1L);
    assertThat(education.createdAt()).isNotNull();
    assertThat(education.updatedAt()).isNotNull();
  }

  @Test
  public void shouldGenerateUuid() {
    var first = factory.createNew("A", "X", Map.of("en", "1"), null, null, true);
    var second = factory.createNew("B", "Y", Map.of("en", "2"), null, null, true);
    assertThat(first.id()).isEqualTo(GENERATED_ID);
    assertThat(second.id()).isEqualTo(GENERATED_ID);
  }

  @Test
  public void shouldThrowExceptionWhenTitleIsBlank() {
    assertThatThrownBy(() -> factory.createNew("  ", "Boston", Map.of("en", "BSc"), START, END,
        false))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenDescriptionHasNoEngEntry() {
    assertThatThrownBy(() -> factory.createNew("MIT", "Boston", Map.of("ru", "Бакалавр"), START,
        END, false))
        .isInstanceOf(DomainValidationException.class);
  }
}
