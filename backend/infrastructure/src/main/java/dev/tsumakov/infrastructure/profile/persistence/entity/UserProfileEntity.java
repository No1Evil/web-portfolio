package dev.tsumakov.infrastructure.profile.persistence.entity;

import dev.tsumakov.infrastructure.core.persistence.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Map;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "user_profile", schema = "profile")
@Getter
@Setter
public class UserProfileEntity {

  @Id
  @Column(name = "user_id")
  private UUID id;

  @MapsId
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private UserEntity user;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "title", columnDefinition = "jsonb")
  private Map<String, String> title;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "description", columnDefinition = "jsonb")
  private Map<String, String> description;
}
