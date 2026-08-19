package dev.tsumakov.infrastructure.profile.experience.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import dev.tsumakov.domain.profile.experience.model.UserExperiencePath;
import dev.tsumakov.infrastructure.profile.experience.persistence.entity.UserExperiencePathEntity;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class UserExperiencePathEntityMapperTest {

  private static final OffsetDateTime NOW = OffsetDateTime.parse("2026-08-18T10:00:00+02:00");
  private static final UUID ID = UUID.randomUUID();

  private final UserExperiencePathEntityMapper mapper = new UserExperiencePathEntityMapperImpl();

  @Test
  void shouldMapDomainToEntity() {
    var domain = new UserExperiencePath(ID, "Google", "Software Engineer", "Mountain View",
        Map.of("en", "Backend"), NOW, NOW.plusYears(2), false, NOW, NOW, 1L);

    var entity = mapper.toEntity(domain);

    assertThat(entity.getId()).isEqualTo(ID);
    assertThat(entity.getTitle()).isEqualTo("Google");
    assertThat(entity.getCompanyName()).isEqualTo("Software Engineer");
    assertThat(entity.getLocation()).isEqualTo("Mountain View");
    assertThat(entity.getDescription()).isEqualTo(Map.of("en", "Backend"));
    assertThat(entity.getStartDate()).isEqualTo(NOW);
    assertThat(entity.getEndDate()).isEqualTo(NOW.plusYears(2));
    assertThat(entity.getPresent()).isFalse();
    assertThat(entity.getCreatedAt()).isEqualTo(NOW);
    assertThat(entity.getUpdatedAt()).isEqualTo(NOW);
    assertThat(entity.getVersion()).isEqualTo(1L);
  }

  @Test
  void shouldMapEntityToDomain() {
    var entity = new UserExperiencePathEntity();
    entity.setId(ID);
    entity.setTitle("Google");
    entity.setCompanyName("Software Engineer");
    entity.setLocation("Mountain View");
    entity.setDescription(Map.of("en", "Backend"));
    entity.setStartDate(NOW);
    entity.setEndDate(NOW.plusYears(2));
    entity.setPresent(false);
    entity.setCreatedAt(NOW);
    entity.setUpdatedAt(NOW);
    entity.setVersion(1L);

    var domain = mapper.toDomain(entity);

    assertThat(domain).isEqualTo(new UserExperiencePath(ID, "Google", "Software Engineer",
        "Mountain View", Map.of("en", "Backend"), NOW, NOW.plusYears(2), false, NOW, NOW, 1L));
  }
}