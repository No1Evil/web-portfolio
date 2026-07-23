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
import jakarta.persistence.Table;
import java.util.Map;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "user_education", schema = "profile")
public class UserEducationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private UserEntity user;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "title", nullable = false, columnDefinition = "jsonb")
  private Map<String, String> institution;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "description", nullable = false, columnDefinition = "jsonb")
  private Map<String, String> degree;

}
