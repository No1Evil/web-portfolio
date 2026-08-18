package dev.tsumakov.infrastructure.core.user.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.domain.core.user.model.User;
import dev.tsumakov.infrastructure.core.user.persistence.entity.UserEntity;
import dev.tsumakov.infrastructure.core.user.persistence.mapper.UserEntityMapper;
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
class UserRepositoryImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.parse("2026-08-18T10:00:00+02:00");

  @Mock
  private UserSpringDataRepository repository;
  @Mock
  private UserEntityMapper mapper;

  private UserRepositoryImpl repositoryImpl;

  @BeforeEach
  void setUp() {
    repositoryImpl = new UserRepositoryImpl(repository, mapper);
  }

  private UserEntity entity(int id) {
    var entity = new UserEntity();
    entity.setId(id);
    entity.setUsername("john");
    entity.setPasswordHash("hash");
    entity.setCreatedAt(NOW);
    entity.setUpdatedAt(NOW);
    entity.setVersion(1L);
    return entity;
  }

  private User domain(int id) {
    return new User(id, "john", "hash", NOW, NOW, 1L);
  }

  @Test
  void shouldFindById() {
    var entity = entity(1);
    var user = domain(1);
    when(repository.findById(1)).thenReturn(Optional.of(entity));
    when(mapper.toDomain(entity)).thenReturn(user);

    var result = repositoryImpl.findById(1);

    assertThat(result).containsSame(user);
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
    var user = domain(1);
    when(repository.findAll()).thenReturn(List.of(entity));
    when(mapper.toDomain(entity)).thenReturn(user);

    var result = repositoryImpl.findAll();

    assertThat(result).containsExactly(user);
  }

  @Test
  void shouldDeleteById() {
    repositoryImpl.delete(1);

    verify(repository).deleteById(1);
  }

  @Test
  void shouldUpdate() {
    var entity = entity(1);
    var user = domain(1);
    var updated = domain(1);
    when(mapper.toEntity(user)).thenReturn(entity);
    when(repository.save(entity)).thenReturn(entity);
    when(mapper.toDomain(entity)).thenReturn(updated);

    var result = repositoryImpl.update(user);

    assertThat(result).isSameAs(updated);
    verify(repository).save(entity);
  }

  @Test
  void shouldCreateWithNullIdBeforeSave() {
    var entity = entity(5);
    var user = domain(1);
    var created = domain(1);
    when(mapper.toEntity(user)).thenReturn(entity);
    when(repository.save(any())).thenReturn(entity);
    when(mapper.toDomain(entity)).thenReturn(created);

    var result = repositoryImpl.create(user);

    assertThat(result).isSameAs(created);
    var captor = ArgumentCaptor.forClass(UserEntity.class);
    verify(repository).save(captor.capture());
    assertThat(captor.getValue().getId()).isNull();
  }
}