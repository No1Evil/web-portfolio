package dev.tsumakov.infrastructure.profile.summary.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.time.OffsetDateTime;
import java.util.Map;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "summary", schema = "profile")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserSummaryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false)
  private String proficiency;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "description", columnDefinition = "jsonb")
  private Map<String, String> description;

  @Column(name = "hero_image_url")
  private String heroImageUrl;

  @Column(name = "created_at", updatable = false)
  private OffsetDateTime createdAt;

  @Column(name = "updated_at")
  private OffsetDateTime updatedAt;

  @Version
  @Column(name = "version", nullable = false)
  private Long version;
}
