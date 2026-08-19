package dev.tsumakov.infrastructure.core.skillcategory.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.domain.core.skillcategory.model.SkillCategory;
import dev.tsumakov.infrastructure.core.skillcategory.persistence.entity.SkillCategoryEntity;
import dev.tsumakov.infrastructure.core.skillcategory.persistence.mapper.SkillCategoryEntityMapper;
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
class SkillCategoryRepositoryImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.parse("2026-08-18T10:00:00+02:00");

  @Mock
  private SkillCategorySpringDataRepository repository;
  @Mock
  private SkillCategoryEntityMapper mapper;

  private SkillCategoryRepositoryImpl repositoryImpl;

  @BeforeEach
  void setUp() {
    repositoryImpl = new SkillCategoryRepositoryImpl(repository, mapper);
  }

  private SkillCategoryEntity entity(int id) {
    var entity = new SkillCategoryEntity();
    entity.setId(id);
    entity.setName("Backend");
    entity.setIconUrl("backend.svg");
    entity.setCreatedAt(NOW);
    entity.setUpdatedAt(NOW);
    entity.setVersion(1L);
    return entity;
  }

  private SkillCategory domain(int id) {
    return new SkillCategory(id, "Backend", "backend.svg", 1L, NOW, NOW);
  }

  @Test
  void shouldFindById() {
    var entity = entity(1);
    var category = domain(1);
    when(repository.findById(1)).thenReturn(Optional.of(entity));
    when(mapper.toDomain(entity)).thenReturn(category);

    var result = repositoryImpl.findById(1);

    assertThat(result).containsSame(category);
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
    var category = domain(1);
    when(repository.findAll()).thenReturn(List.of(entity));
    when(mapper.toDomain(entity)).thenReturn(category);

    var result = repositoryImpl.findAll();

    assertThat(result).containsExactly(category);
  }

  @Test
  void shouldDeleteById() {
    repositoryImpl.delete(1);

    verify(repository).deleteById(1);
  }

  @Test
  void shouldUpdate() {
    var entity = entity(1);
    var category = domain(1);
    var updated = domain(1);
    when(mapper.toEntity(category)).thenReturn(entity);
    when(repository.save(entity)).thenReturn(entity);
    when(mapper.toDomain(entity)).thenReturn(updated);

    var result = repositoryImpl.update(category);

    assertThat(result).isSameAs(updated);
    verify(repository).save(entity);
  }

  @Test
  void shouldCreateWithNullIdBeforeSave() {
    var entity = entity(5);
    var category = domain(1);
    var created = domain(5);
    when(mapper.toEntity(category)).thenReturn(entity);
    when(repository.save(any())).thenReturn(entity);
    when(mapper.toDomain(entity)).thenReturn(created);

    var result = repositoryImpl.create(category);

    assertThat(result).isSameAs(created);
    var captor = ArgumentCaptor.forClass(SkillCategoryEntity.class);
    verify(repository).save(captor.capture());
    assertThat(captor.getValue().getId()).isNull();
  }
}