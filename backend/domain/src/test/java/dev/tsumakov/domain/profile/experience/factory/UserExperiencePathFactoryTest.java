package dev.tsumakov.domain.profile.experience.factory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import dev.tsumakov.domain.shared.exception.DomainValidationException;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class UserExperiencePathFactoryTest {

  private static final UUID GENERATED_ID = UUID.randomUUID();
  private static final OffsetDateTime START = OffsetDateTime.parse("2020-01-01T10:00:00Z");
  private static final OffsetDateTime END = OffsetDateTime.parse("2024-01-01T10:00:00Z");

  private final UserExperiencePathFactory factory =
      new UserExperiencePathFactory(() -> GENERATED_ID);

  @Test
  public void shouldCreateNewExperienceWithDefaultsAndGeneratedId() {
    var experience = factory.createNew("Software Engineer", "Acme Corp", "Berlin",
        Map.of("en", "Built stuff"), START, END, false);

    assertThat(experience.id()).isEqualTo(GENERATED_ID);
    assertThat(experience.title()).isEqualTo("Software Engineer");
    assertThat(experience.companyName()).isEqualTo("Acme Corp");
    assertThat(experience.location()).isEqualTo("Berlin");
    assertThat(experience.description()).containsEntry("en", "Built stuff");
    assertThat(experience.startDate()).isEqualTo(START);
    assertThat(experience.endDate()).isEqualTo(END);
    assertThat(experience.present()).isFalse();
    assertThat(experience.version()).isEqualTo(1L);
    assertThat(experience.createdAt()).isNotNull();
    assertThat(experience.updatedAt()).isNotNull();
  }

  @Test
  public void shouldUseUuidGeneratorForEachCall() {
    var first = factory.createNew("A", "B", "C", Map.of("en", "1"), null, null, true);
    var second = factory.createNew("D", "E", "F", Map.of("en", "2"), null, null, true);
    assertThat(first.id()).isEqualTo(GENERATED_ID);
    assertThat(second.id()).isEqualTo(GENERATED_ID);
  }

  @Test
  public void shouldThrowExceptionWhenCompanyNameIsBlank() {
    assertThatThrownBy(() -> factory.createNew("Software Engineer", "  ", "Berlin",
        Map.of("en", "Built stuff"), START, END, false))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenLocationIsBlank() {
    assertThatThrownBy(() -> factory.createNew("Software Engineer", "Acme Corp", " ",
        Map.of("en", "Built stuff"), START, END, false))
        .isInstanceOf(DomainValidationException.class);
  }
}
