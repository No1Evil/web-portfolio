package dev.tsumakov.infrastructure.shared.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class UuidGeneratorImplTest {

  private final UuidGeneratorImpl generator = new UuidGeneratorImpl();

  @Test
  void shouldGenerateValidUuid() {
    var uuid = generator.generate();

    assertThat(uuid).isNotNull();
    assertThat(UUID.fromString(uuid.toString())).isEqualTo(uuid);
  }
}