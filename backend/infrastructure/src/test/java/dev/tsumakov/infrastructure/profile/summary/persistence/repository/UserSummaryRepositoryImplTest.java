package dev.tsumakov.infrastructure.profile.summary.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.domain.profile.summary.model.UserSummary;
import dev.tsumakov.infrastructure.profile.summary.persistence.entity.UserSummaryEntity;
import dev.tsumakov.infrastructure.profile.summary.persistence.mapper.UserSummaryEntityMapper;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserSummaryRepositoryImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.parse("2026-08-18T10:00:00+02:00");

  @Mock
  private UserSummarySpringDataRepository repository;
  @Mock
  private UserSummaryEntityMapper mapper;

  private UserSummaryRepositoryImpl repositoryImpl;

  @BeforeEach
  void setUp() {
    repositoryImpl = new UserSummaryRepositoryImpl(repository, mapper);
  }

  private UserSummaryEntity entity(int id) {
    var entity = new UserSummaryEntity();
    entity.setId(id);
    entity.setFirstName("John");
    entity.setLastName("Doe");
    entity.setProficiency("Senior");
    entity.setDescription(Map.of("en", "desc"));
    entity.setHeroImageUrl("hero.svg");
    entity.setCreatedAt(NOW);
    entity.setUpdatedAt(NOW);
    entity.setVersion(1L);
    return entity;
  }

  private UserSummary domain(int id) {
    return new UserSummary(id, "John", "Doe", "Senior", Map.of("en", "desc"), "hero.svg", NOW,
        NOW, 1L);
  }

  @Test
  void shouldFindById() {
    var entity = entity(1);
    var summary = domain(1);
    when(repository.findById(1)).thenReturn(Optional.of(entity));
    when(mapper.toDomain(entity)).thenReturn(summary);

    var result = repositoryImpl.findById(1);

    assertThat(result).containsSame(summary);
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
    var summary = domain(1);
    when(repository.findAll()).thenReturn(List.of(entity));
    when(mapper.toDomain(entity)).thenReturn(summary);

    var result = repositoryImpl.findAll();

    assertThat(result).containsExactly(summary);
  }

  @Test
  void shouldDeleteById() {
    repositoryImpl.delete(1);

    verify(repository).deleteById(1);
  }

  @Test
  void shouldUpdate() {
    var entity = entity(1);
    var summary = domain(1);
    var updated = domain(1);
    when(mapper.toEntity(summary)).thenReturn(entity);
    when(repository.save(entity)).thenReturn(entity);
    when(mapper.toDomain(entity)).thenReturn(updated);

    var result = repositoryImpl.update(summary);

    assertThat(result).isSameAs(updated);
    verify(repository).save(entity);
  }

  @Test
  void shouldCreateWithoutNullingId() {
    var entity = entity(1);
    var summary = domain(1);
    when(mapper.toEntity(summary)).thenReturn(entity);
    when(repository.save(any())).thenReturn(entity);
    when(mapper.toDomain(entity)).thenReturn(summary);

    var result = repositoryImpl.create(summary);

    assertThat(result).isSameAs(summary);
    var captor = ArgumentCaptor.forClass(UserSummaryEntity.class);
    verify(repository).save(captor.capture());
    assertThat(captor.getValue().getId()).isEqualTo(1);
  }
}