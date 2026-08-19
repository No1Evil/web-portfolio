package dev.tsumakov.infrastructure.core.user.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import dev.tsumakov.domain.core.user.model.User;
import dev.tsumakov.infrastructure.core.user.persistence.entity.UserEntity;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;

class UserEntityMapperTest {

  private static final OffsetDateTime NOW = OffsetDateTime.parse("2026-08-18T10:00:00+02:00");

  private final UserEntityMapper mapper = new UserEntityMapperImpl();

  @Test
  void shouldMapDomainToEntity() {
    var domain = new User(1, "john", "hash", NOW, NOW, 1L);

    var entity = mapper.toEntity(domain);

    assertThat(entity.getId()).isEqualTo(1);
    assertThat(entity.getUsername()).isEqualTo("john");
    assertThat(entity.getPasswordHash()).isEqualTo("hash");
    assertThat(entity.getCreatedAt()).isEqualTo(NOW);
    assertThat(entity.getUpdatedAt()).isEqualTo(NOW);
    assertThat(entity.getVersion()).isEqualTo(1L);
  }

  @Test
  void shouldMapEntityToDomain() {
    var entity = new UserEntity();
    entity.setId(1);
    entity.setUsername("john");
    entity.setPasswordHash("hash");
    entity.setCreatedAt(NOW);
    entity.setUpdatedAt(NOW);
    entity.setVersion(1L);

    var domain = mapper.toDomain(entity);

    assertThat(domain).isEqualTo(new User(1, "john", "hash", NOW, NOW, 1L));
  }
}