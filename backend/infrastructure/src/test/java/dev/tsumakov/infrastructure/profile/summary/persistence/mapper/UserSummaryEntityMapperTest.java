package dev.tsumakov.infrastructure.profile.summary.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import dev.tsumakov.domain.profile.summary.model.UserSummary;
import dev.tsumakov.infrastructure.profile.summary.persistence.entity.UserSummaryEntity;
import java.time.OffsetDateTime;
import java.util.Map;
import org.junit.jupiter.api.Test;

class UserSummaryEntityMapperTest {

  private static final OffsetDateTime NOW = OffsetDateTime.parse("2026-08-18T10:00:00+02:00");

  private final UserSummaryEntityMapper mapper = new UserSummaryEntityMapperImpl();

  @Test
  void shouldMapDomainToEntity() {
    var domain = new UserSummary(1, "John", "Doe", "Senior", Map.of("en", "desc"), "hero.svg",
        NOW, NOW, 1L);

    var entity = mapper.toEntity(domain);

    assertThat(entity.getId()).isEqualTo(1);
    assertThat(entity.getFirstName()).isEqualTo("John");
    assertThat(entity.getLastName()).isEqualTo("Doe");
    assertThat(entity.getProficiency()).isEqualTo("Senior");
    assertThat(entity.getDescription()).isEqualTo(Map.of("en", "desc"));
    assertThat(entity.getHeroImageUrl()).isEqualTo("hero.svg");
    assertThat(entity.getCreatedAt()).isEqualTo(NOW);
    assertThat(entity.getUpdatedAt()).isEqualTo(NOW);
    assertThat(entity.getVersion()).isEqualTo(1L);
  }

  @Test
  void shouldMapEntityToDomain() {
    var entity = new UserSummaryEntity();
    entity.setId(1);
    entity.setFirstName("John");
    entity.setLastName("Doe");
    entity.setProficiency("Senior");
    entity.setDescription(Map.of("en", "desc"));
    entity.setHeroImageUrl("hero.svg");
    entity.setCreatedAt(NOW);
    entity.setUpdatedAt(NOW);
    entity.setVersion(1L);

    var domain = mapper.toDomain(entity);

    assertThat(domain).isEqualTo(new UserSummary(1, "John", "Doe", "Senior", Map.of("en", "desc"),
        "hero.svg", NOW, NOW, 1L));
  }
}