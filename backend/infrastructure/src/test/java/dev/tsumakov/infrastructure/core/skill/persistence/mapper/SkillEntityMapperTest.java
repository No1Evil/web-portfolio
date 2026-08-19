package dev.tsumakov.infrastructure.core.skill.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import dev.tsumakov.domain.core.skill.model.Skill;
import dev.tsumakov.infrastructure.core.skillcategory.persistence.entity.SkillCategoryEntity;
import dev.tsumakov.infrastructure.core.skill.persistence.entity.SkillEntity;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;

class SkillEntityMapperTest {

  private static final OffsetDateTime NOW = OffsetDateTime.parse("2026-08-18T10:00:00+02:00");

  private final SkillEntityMapper mapper = new SkillEntityMapperImpl();

  @Test
  void shouldMapDomainToEntity() {
    var domain = new Skill(1, 3, "Java", "java.svg", NOW, NOW, 1L);

    var entity = mapper.toEntity(domain);

    assertThat(entity.getId()).isEqualTo(1);
    assertThat(entity.getSkillCategory()).isNotNull();
    assertThat(entity.getSkillCategory().getId()).isEqualTo(3);
    assertThat(entity.getName()).isEqualTo("Java");
    assertThat(entity.getIconUrl()).isEqualTo("java.svg");
    assertThat(entity.getCreatedAt()).isEqualTo(NOW);
    assertThat(entity.getUpdatedAt()).isEqualTo(NOW);
    assertThat(entity.getVersion()).isEqualTo(1L);
  }

  @Test
  void shouldMapEntityToDomain() {
    var category = new SkillCategoryEntity();
    category.setId(3);
    var entity = new SkillEntity();
    entity.setId(1);
    entity.setSkillCategory(category);
    entity.setName("Java");
    entity.setIconUrl("java.svg");
    entity.setCreatedAt(NOW);
    entity.setUpdatedAt(NOW);
    entity.setVersion(1L);

    var domain = mapper.toDomain(entity);

    assertThat(domain).isEqualTo(new Skill(1, 3, "Java", "java.svg", NOW, NOW, 1L));
  }
}