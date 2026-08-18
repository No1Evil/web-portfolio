package dev.tsumakov.infrastructure.core.skillcategory.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import dev.tsumakov.domain.core.skillcategory.model.SkillCategory;
import dev.tsumakov.infrastructure.core.skillcategory.persistence.entity.SkillCategoryEntity;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;

class SkillCategoryEntityMapperTest {

  private static final OffsetDateTime NOW = OffsetDateTime.parse("2026-08-18T10:00:00+02:00");

  private final SkillCategoryEntityMapper mapper = new SkillCategoryEntityMapperImpl();

  @Test
  void shouldMapDomainToEntity() {
    var domain = new SkillCategory(1, "Backend", "backend.svg", 1L, NOW, NOW);

    var entity = mapper.toEntity(domain);

    assertThat(entity.getId()).isEqualTo(1);
    assertThat(entity.getName()).isEqualTo("Backend");
    assertThat(entity.getIconUrl()).isEqualTo("backend.svg");
    assertThat(entity.getVersion()).isEqualTo(1L);
    assertThat(entity.getCreatedAt()).isEqualTo(NOW);
    assertThat(entity.getUpdatedAt()).isEqualTo(NOW);
  }

  @Test
  void shouldMapEntityToDomain() {
    var entity = new SkillCategoryEntity();
    entity.setId(1);
    entity.setName("Backend");
    entity.setIconUrl("backend.svg");
    entity.setVersion(1L);
    entity.setCreatedAt(NOW);
    entity.setUpdatedAt(NOW);

    var domain = mapper.toDomain(entity);

    assertThat(domain).isEqualTo(new SkillCategory(1, "Backend", "backend.svg", 1L, NOW, NOW));
  }
}