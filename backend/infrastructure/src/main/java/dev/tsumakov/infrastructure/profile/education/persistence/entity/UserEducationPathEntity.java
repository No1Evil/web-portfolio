package dev.tsumakov.infrastructure.profile.education.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.generator.EventType;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "education_paths", schema = "profile")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserEducationPathEntity {

  @Id
  @Generated(event = EventType.INSERT)
  private UUID id;

  @Column(nullable = false)
  private String title;

  @Column(length = 100, nullable = false)
  private String location;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "description", columnDefinition = "jsonb")
  private Map<String, String> description;

  @Column(name = "start_date")
  private OffsetDateTime startDate;

  @Column(name = "end_date")
  private OffsetDateTime endDate;

  @Column
  private Boolean present;

  @Column(name = "created_at", updatable = false)
  private OffsetDateTime createdAt;

  @Column(name = "updated_at")
  private OffsetDateTime updatedAt;

  @Version
  @Column(name = "version", nullable = false)
  private Long version;

}
