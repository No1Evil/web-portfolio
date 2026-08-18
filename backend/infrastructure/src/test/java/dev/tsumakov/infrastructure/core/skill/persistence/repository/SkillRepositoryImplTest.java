package dev.tsumakov.infrastructure.core.skill.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.domain.core.skill.model.Skill;
import dev.tsumakov.infrastructure.core.skill.persistence.entity.SkillEntity;
import dev.tsumakov.infrastructure.core.skill.persistence.mapper.SkillEntityMapper;
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
class SkillRepositoryImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.parse("2026-08-18T10:00:00+02:00");

  @Mock
  private SkillSpringDataRepository repository;
  @Mock
  private SkillEntityMapper mapper;

  private SkillRepositoryImpl repositoryImpl;

  @BeforeEach
  void setUp() {
    repositoryImpl = new SkillRepositoryImpl(repository, mapper);
  }

  private SkillEntity entity(int id) {
    var entity = new SkillEntity();
    entity.setId(id);
    entity.setName("Java");
    entity.setIconUrl("java.svg");
    entity.setCreatedAt(NOW);
    entity.setUpdatedAt(NOW);
    entity.setVersion(1L);
    return entity;
  }

  private Skill domain(int id) {
    return new Skill(id, 1, "Java", "java.svg", NOW, NOW, 1L);
  }

  @Test
  void shouldFindById() {
    var entity = entity(1);
    var skill = domain(1);
    when(repository.findById(1)).thenReturn(Optional.of(entity));
    when(mapper.toDomain(entity)).thenReturn(skill);

    var result = repositoryImpl.findById(1);

    assertThat(result).containsSame(skill);
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
    var skill = domain(1);
    when(repository.findAll()).thenReturn(List.of(entity));
    when(mapper.toDomain(entity)).thenReturn(skill);

    var result = repositoryImpl.findAll();

    assertThat(result).containsExactly(skill);
  }

  @Test
  void shouldDeleteById() {
    repositoryImpl.delete(1);

    verify(repository).deleteById(1);
  }

  @Test
  void shouldCreateWithNullIdBeforeSave() {
    var entity = entity(5);
    var skill = domain(1);
    var created = domain(5);
    when(mapper.toEntity(skill)).thenReturn(entity);
    when(repository.save(any())).thenReturn(entity);
    when(mapper.toDomain(entity)).thenReturn(created);

    var result = repositoryImpl.create(skill);

    assertThat(result).isSameAs(created);
    var captor = ArgumentCaptor.forClass(SkillEntity.class);
    verify(repository).save(captor.capture());
    assertThat(captor.getValue().getId()).isNull();
  }

  @Test
  void shouldUpdate() {
    var entity = entity(1);
    var skill = domain(1);
    var updated = domain(1);
    when(mapper.toEntity(skill)).thenReturn(entity);
    when(repository.save(entity)).thenReturn(entity);
    when(mapper.toDomain(entity)).thenReturn(updated);

    var result = repositoryImpl.update(skill);

    assertThat(result).isSameAs(updated);
    verify(repository).save(entity);
  }
}