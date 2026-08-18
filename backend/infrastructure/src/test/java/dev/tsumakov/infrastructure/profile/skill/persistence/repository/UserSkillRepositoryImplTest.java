package dev.tsumakov.infrastructure.profile.skill.persistence.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.domain.core.skill.model.Skill;
import dev.tsumakov.infrastructure.core.skill.persistence.entity.SkillEntity;
import dev.tsumakov.infrastructure.core.skill.persistence.mapper.SkillEntityMapper;
import java.time.OffsetDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserSkillRepositoryImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.parse("2026-08-18T10:00:00+02:00");

  @Mock
  private UserSkillSpringDataRepository repository;
  @Mock
  private SkillEntityMapper mapper;

  private UserSkillRepositoryImpl repositoryImpl;

  @BeforeEach
  void setUp() {
    repositoryImpl = new UserSkillRepositoryImpl(repository, mapper);
  }

  @Test
  void shouldAddSkill() {
    repositoryImpl.addSkill(1, 3);

    verify(repository).addSkill(1, 3);
  }

  @Test
  void shouldRemoveSkill() {
    repositoryImpl.removeSkill(1, 3);

    verify(repository).removeSkill(1, 3);
  }

  @Test
  void shouldFindAllByUserId() {
    var entity = new SkillEntity();
    entity.setId(3);
    entity.setName("Java");
    entity.setIconUrl("java.svg");
    entity.setCreatedAt(NOW);
    entity.setUpdatedAt(NOW);
    entity.setVersion(1L);
    var skill = new Skill(3, 1, "Java", "java.svg", NOW, NOW, 1L);
    when(repository.findAllByUserId(1)).thenReturn(List.of(entity));
    when(mapper.toDomain(entity)).thenReturn(skill);

    var result = repositoryImpl.findAllByUserId(1);

    assertThat(result).containsExactly(skill);
  }

  @Test
  void shouldReturnExists() {
    when(repository.exists(1, 3)).thenReturn(true);

    assertThat(repositoryImpl.exists(1, 3)).isTrue();
  }
}