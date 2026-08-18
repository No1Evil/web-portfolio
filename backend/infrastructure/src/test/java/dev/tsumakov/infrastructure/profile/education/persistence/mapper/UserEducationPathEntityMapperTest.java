package dev.tsumakov.infrastructure.profile.education.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import dev.tsumakov.domain.profile.education.model.UserEducationPath;
import dev.tsumakov.infrastructure.profile.education.persistence.entity.UserEducationPathEntity;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class UserEducationPathEntityMapperTest {

  private static final OffsetDateTime NOW = OffsetDateTime.parse("2026-08-18T10:00:00+02:00");
  private static final UUID ID = UUID.randomUUID();

  private final UserEducationPathEntityMapper mapper = new UserEducationPathEntityMapperImpl();

  @Test
  void shouldMapDomainToEntity() {
    var domain = new UserEducationPath(ID, "MIT", "Boston", Map.of("en", "BSc"), NOW,
        NOW.plusYears(4), true, NOW, NOW, 1L);

    var entity = mapper.toEntity(domain);

    assertThat(entity.getId()).isEqualTo(ID);
    assertThat(entity.getTitle()).isEqualTo("MIT");
    assertThat(entity.getLocation()).isEqualTo("Boston");
    assertThat(entity.getDescription()).isEqualTo(Map.of("en", "BSc"));
    assertThat(entity.getStartDate()).isEqualTo(NOW);
    assertThat(entity.getEndDate()).isEqualTo(NOW.plusYears(4));
    assertThat(entity.getPresent()).isTrue();
    assertThat(entity.getCreatedAt()).isEqualTo(NOW);
    assertThat(entity.getUpdatedAt()).isEqualTo(NOW);
    assertThat(entity.getVersion()).isEqualTo(1L);
  }

  @Test
  void shouldMapEntityToDomain() {
    var entity = new UserEducationPathEntity();
    entity.setId(ID);
    entity.setTitle("MIT");
    entity.setLocation("Boston");
    entity.setDescription(Map.of("en", "BSc"));
    entity.setStartDate(NOW);
    entity.setEndDate(NOW.plusYears(4));
    entity.setPresent(true);
    entity.setCreatedAt(NOW);
    entity.setUpdatedAt(NOW);
    entity.setVersion(1L);

    var domain = mapper.toDomain(entity);

    assertThat(domain).isEqualTo(new UserEducationPath(ID, "MIT", "Boston", Map.of("en", "BSc"),
        NOW, NOW.plusYears(4), true, NOW, NOW, 1L));
  }
}