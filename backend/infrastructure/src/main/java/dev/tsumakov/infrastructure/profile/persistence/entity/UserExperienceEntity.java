package dev.tsumakov.infrastructure.profile.persistence.entity;

import dev.tsumakov.infrastructure.core.persistence.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.Map;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "user_experience", schema = "profile")
public class UserExperienceEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private UserEntity user;

  @Column(name = "company", nullable = false)
  private String company;

  @Column(name = "position")
  private String position;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "description", columnDefinition = "jsonb")
  private Map<String, String> description;

  @Column(name = "start_date")
  private OffsetDateTime startDate;

  @Column(name = "end_date")
  private OffsetDateTime endDate;
}
