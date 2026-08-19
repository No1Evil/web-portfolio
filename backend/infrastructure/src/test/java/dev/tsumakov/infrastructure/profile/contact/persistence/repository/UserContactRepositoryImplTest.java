package dev.tsumakov.infrastructure.profile.contact.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.domain.profile.contact.model.UserContact;
import dev.tsumakov.infrastructure.profile.contact.persistence.entity.UserContactEntity;
import dev.tsumakov.infrastructure.profile.contact.persistence.mapper.UserContactEntityMapper;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserContactRepositoryImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.parse("2026-08-18T10:00:00+02:00");

  @Mock
  private UserContactSpringDataRepository repository;
  @Mock
  private UserContactEntityMapper mapper;

  private UserContactRepositoryImpl repositoryImpl;

  @BeforeEach
  void setUp() {
    repositoryImpl = new UserContactRepositoryImpl(repository, mapper);
  }

  private UserContactEntity entity(int id) {
    var entity = new UserContactEntity();
    entity.setId(id);
    entity.setTitle("Email");
    entity.setRedirectUrl("mailto:john@example.com");
    entity.setIconUrl("mail.svg");
    entity.setCreatedAt(NOW);
    entity.setUpdatedAt(NOW);
    entity.setVersion(1L);
    return entity;
  }

  private UserContact domain(int id) {
    return new UserContact(id, "Email", "mailto:john@example.com", "mail.svg", NOW, NOW, 1L);
  }

  @Test
  void shouldFindById() {
    var entity = entity(1);
    var contact = domain(1);
    when(repository.findById(1)).thenReturn(Optional.of(entity));
    when(mapper.toDomain(entity)).thenReturn(contact);

    var result = repositoryImpl.findById(1);

    assertThat(result).containsSame(contact);
  }

  @Test
  void shouldReturnEmptyWhenNotFound() {
    when(repository.findById(1)).thenReturn(Optional.empty());

    var result = repositoryImpl.findById(1);

    assertThat(result).isEmpty();
  }

  @Test
  void shouldReturnExists() {
    when(repository.existsById(1)).thenReturn(true);

    assertThat(repositoryImpl.existsById(1)).isTrue();
  }

  @Test
  void shouldFindAll() {
    var entity = entity(1);
    var contact = domain(1);
    when(repository.findAll()).thenReturn(List.of(entity));
    when(mapper.toDomain(entity)).thenReturn(contact);

    var result = repositoryImpl.findAll();

    assertThat(result).containsExactly(contact);
  }

  @Test
  void shouldDeleteById() {
    repositoryImpl.delete(1);

    verify(repository).deleteById(1);
  }

  @Test
  void shouldUpdate() {
    var entity = entity(1);
    var contact = domain(1);
    var updated = domain(1);
    when(mapper.toEntity(contact)).thenReturn(entity);
    when(repository.save(entity)).thenReturn(entity);
    when(mapper.toDomain(entity)).thenReturn(updated);

    var result = repositoryImpl.update(contact);

    assertThat(result).isSameAs(updated);
    verify(repository).save(entity);
  }

  @Test
  void shouldCreateWithNullIdBeforeSave() {
    var entity = entity(5);
    var contact = domain(1);
    var created = domain(5);
    when(mapper.toEntity(contact)).thenReturn(entity);
    when(repository.save(any())).thenReturn(entity);
    when(mapper.toDomain(entity)).thenReturn(created);

    var result = repositoryImpl.create(contact);

    assertThat(result).isSameAs(created);
    var captor = ArgumentCaptor.forClass(UserContactEntity.class);
    verify(repository).save(captor.capture());
    assertThat(captor.getValue().getId()).isNull();
  }
}