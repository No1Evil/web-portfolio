package dev.tsumakov.infrastructure.profile.contact.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import dev.tsumakov.domain.profile.contact.model.UserContact;
import dev.tsumakov.infrastructure.profile.contact.persistence.entity.UserContactEntity;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.Test;

class UserContactEntityMapperTest {

  private static final OffsetDateTime NOW = OffsetDateTime.parse("2026-08-18T10:00:00+02:00");

  private final UserContactEntityMapper mapper = new UserContactEntityMapperImpl();

  @Test
  void shouldMapDomainToEntity() {
    var domain = new UserContact(1, "Email", "mailto:john@example.com", "mail.svg", NOW, NOW, 1L);

    var entity = mapper.toEntity(domain);

    assertThat(entity.getId()).isEqualTo(1);
    assertThat(entity.getTitle()).isEqualTo("Email");
    assertThat(entity.getRedirectUrl()).isEqualTo("mailto:john@example.com");
    assertThat(entity.getIconUrl()).isEqualTo("mail.svg");
    assertThat(entity.getCreatedAt()).isEqualTo(NOW);
    assertThat(entity.getUpdatedAt()).isEqualTo(NOW);
    assertThat(entity.getVersion()).isEqualTo(1L);
  }

  @Test
  void shouldMapEntityToDomain() {
    var entity = new UserContactEntity();
    entity.setId(1);
    entity.setTitle("Email");
    entity.setRedirectUrl("mailto:john@example.com");
    entity.setIconUrl("mail.svg");
    entity.setCreatedAt(NOW);
    entity.setUpdatedAt(NOW);
    entity.setVersion(1L);

    var domain = mapper.toDomain(entity);

    assertThat(domain).isEqualTo(new UserContact(1, "Email", "mailto:john@example.com", "mail.svg",
        NOW, NOW, 1L));
  }
}