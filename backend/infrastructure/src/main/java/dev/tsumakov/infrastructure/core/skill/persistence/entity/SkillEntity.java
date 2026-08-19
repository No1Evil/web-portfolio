package dev.tsumakov.infrastructure.core.skill.persistence.entity;

import dev.tsumakov.infrastructure.core.skillcategory.persistence.entity.SkillCategoryEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;
import java.time.OffsetDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "skills",
    schema = "core",
    uniqueConstraints = @UniqueConstraint(
        name = "uq_skills_category_name",
        columnNames = {"category_id", "name"}
    ))
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SkillEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 50, unique = true, nullable = false)
  @EqualsAndHashCode.Include
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id")
  private SkillCategoryEntity skillCategory;

  @Column
  private String iconUrl;

  @Column(name = "created_at", updatable = false)
  private OffsetDateTime createdAt;

  @Column(name = "updated_at")
  private OffsetDateTime updatedAt;

  @Version
  @Column(name = "version", nullable = false)
  private Long version;
}
