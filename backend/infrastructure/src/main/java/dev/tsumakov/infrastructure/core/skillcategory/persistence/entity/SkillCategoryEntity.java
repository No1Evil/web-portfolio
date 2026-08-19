package dev.tsumakov.infrastructure.core.skillcategory.persistence.entity;

import dev.tsumakov.infrastructure.core.skill.persistence.entity.SkillEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.time.OffsetDateTime;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "skill_categories", schema = "core")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SkillCategoryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 50, unique = true, nullable = false)
  @EqualsAndHashCode.Include
  private String name;

  @OneToMany(mappedBy = "skillCategory", fetch = FetchType.LAZY)
  private Set<SkillEntity> skills;

  @Column(name = "icon_url")
  private String iconUrl;

  @Column(name = "created_at", updatable = false)
  private OffsetDateTime createdAt;

  @Column(name = "updated_at")
  private OffsetDateTime updatedAt;

  @Version
  @Column(name = "version", nullable = false)
  private Long version;

}
