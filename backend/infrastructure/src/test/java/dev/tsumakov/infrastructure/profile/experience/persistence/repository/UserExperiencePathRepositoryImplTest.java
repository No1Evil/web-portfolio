package dev.tsumakov.infrastructure.profile.experience.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.domain.profile.experience.model.UserExperiencePath;
import dev.tsumakov.infrastructure.profile.experience.persistence.entity.UserExperiencePathEntity;
import dev.tsumakov.infrastructure.profile.experience.persistence.mapper.UserExperiencePathEntityMapper;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserExperiencePathRepositoryImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.parse("2026-08-18T10:00:00+02:00");
  private static final UUID ID = UUID.randomUUID();

  @Mock
  private UserExperiencePathSpringDataRepository repository;
  @Mock
  private UserExperiencePathEntityMapper mapper;

  private UserExperiencePathRepositoryImpl repositoryImpl;

  @BeforeEach
  void setUp() {
    repositoryImpl = new UserExperiencePathRepositoryImpl(repository, mapper);
  }

  private UserExperiencePathEntity entity() {
    var entity = new UserExperiencePathEntity();
    entity.setId(ID);
    entity.setTitle("Google");
    entity.setCompanyName("Google");
    entity.setLocation("Mountain View");
    entity.setDescription(Map.of("en", "Backend"));
    entity.setStartDate(NOW);
    entity.setEndDate(NOW.plusYears(2));
    entity.setPresent(false);
    entity.setCreatedAt(NOW);
    entity.setUpdatedAt(NOW);
    entity.setVersion(1L);
    return entity;
  }

  private UserExperiencePath domain() {
    return new UserExperiencePath(ID, "Google", "Software Engineer", "Mountain View",
        Map.of("en", "Backend"), NOW, NOW.plusYears(2), false, NOW, NOW, 1L);
  }

  @Test
  void shouldFindById() {
    var entity = entity();
    var path = domain();
    when(repository.findById(ID)).thenReturn(Optional.of(entity));
    when(mapper.toDomain(entity)).thenReturn(path);

    var result = repositoryImpl.findById(ID);

    assertThat(result).containsSame(path);
  }

  @Test
  void shouldReturnEmptyWhenNotFound() {
    when(repository.findById(ID)).thenReturn(Optional.empty());

    var result = repositoryImpl.findById(ID);

    assertThat(result).isEmpty();
  }

  @Test
  void shouldReturnExists() {
    when(repository.existsById(ID)).thenReturn(true);

    assertThat(repositoryImpl.existsById(ID)).isTrue();
  }

  @Test
  void shouldFindAll() {
    var entity = entity();
    var path = domain();
    when(repository.findAll()).thenReturn(List.of(entity));
    when(mapper.toDomain(entity)).thenReturn(path);

    var result = repositoryImpl.findAll();

    assertThat(result).containsExactly(path);
  }

  @Test
  void shouldDeleteById() {
    repositoryImpl.delete(ID);

    verify(repository).deleteById(ID);
  }

  @Test
  void shouldUpdate() {
    var entity = entity();
    var path = domain();
    when(mapper.toEntity(path)).thenReturn(entity);
    when(repository.save(entity)).thenReturn(entity);
    when(mapper.toDomain(entity)).thenReturn(path);

    var result = repositoryImpl.update(path);

    assertThat(result).isSameAs(path);
    verify(repository).save(entity);
  }

  @Test
  void shouldCreateWithoutNullingId() {
    var entity = entity();
    var path = domain();
    when(mapper.toEntity(path)).thenReturn(entity);
    when(repository.save(any())).thenReturn(entity);
    when(mapper.toDomain(entity)).thenReturn(path);

    var result = repositoryImpl.create(path);

    assertThat(result).isSameAs(path);
    var captor = ArgumentCaptor.forClass(UserExperiencePathEntity.class);
    verify(repository).save(captor.capture());
    assertThat(captor.getValue().getId()).isEqualTo(ID);
  }
}