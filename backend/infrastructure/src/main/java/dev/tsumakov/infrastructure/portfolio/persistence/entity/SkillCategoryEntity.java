package dev.tsumakov.infrastructure.portfolio.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "skill_categories", schema = "portfolio")
@Getter
@Setter
public class SkillCategoryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(length = 50, unique = true, nullable = false)
  private String name;

  @Column(name = "icon_url", columnDefinition = "text")
  private String iconUrl;
}
