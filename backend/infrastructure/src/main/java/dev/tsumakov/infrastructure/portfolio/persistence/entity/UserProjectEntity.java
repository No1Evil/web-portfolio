package dev.tsumakov.infrastructure.portfolio.persistence.entity;

import dev.tsumakov.infrastructure.core.persistence.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "user_projects", schema = "portfolio")
@Getter
@Setter
public class UserProjectEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private UserEntity user;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "title", columnDefinition = "jsonb")
  private Map<String, String> title;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "description", columnDefinition = "jsonb")
  private Map<String, String> description;

  @Column(name = "is_featured", nullable = false)
  private Boolean isFeatured = false;

  @Column(name = "project_url")
  private String projectUrl;

  @Column(name = "preview_image_url")
  private String previewImageUrl;
}
