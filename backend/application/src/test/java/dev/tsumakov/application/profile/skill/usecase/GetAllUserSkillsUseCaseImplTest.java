package dev.tsumakov.application.profile.skill.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.profile.skill.dto.outer.UserSkillDto;
import dev.tsumakov.application.profile.skill.mapper.UserSkillDtoMapper;
import dev.tsumakov.domain.core.skill.model.Skill;
import dev.tsumakov.domain.core.skill.repository.UserSkillRepository;
import java.time.OffsetDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetAllUserSkillsUseCaseImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.now();

  @Mock
  private UserSkillRepository repository;
  @Mock
  private UserSkillDtoMapper mapper;

  private GetAllUserSkillsUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new GetAllUserSkillsUseCaseImpl(repository, mapper);
  }

  @Test
  void shouldReturnAllSkillsForHardcodedUserId() {
    var java = new Skill(1, 1, "Java", "icon.svg", NOW, NOW, 1L);
    var kotlin = new Skill(2, 1, "Kotlin", null, NOW, NOW, 1L);
    var javaDto = new UserSkillDto(1, 1, "Java", "icon.svg");
    var kotlinDto = new UserSkillDto(2, 1, "Kotlin", null);

    when(repository.findAllByUserId(1)).thenReturn(List.of(java, kotlin));
    when(mapper.toDto(java)).thenReturn(javaDto);
    when(mapper.toDto(kotlin)).thenReturn(kotlinDto);

    var result = useCase.execute();

    assertThat(result).containsExactly(javaDto, kotlinDto);
    verify(repository).findAllByUserId(1);
    verify(mapper).toDto(java);
    verify(mapper).toDto(kotlin);
  }

  @Test
  void shouldReturnEmptyListWhenRepositoryEmpty() {
    when(repository.findAllByUserId(1)).thenReturn(List.of());

    var result = useCase.execute();

    assertThat(result).isEmpty();
    verify(repository).findAllByUserId(1);
    verify(mapper, never()).toDto(org.mockito.ArgumentMatchers.any());
  }

  @Test
  void shouldThrowWhenRepositoryFails() {
    when(repository.findAllByUserId(1)).thenThrow(new RuntimeException("db down"));

    assertThatThrownBy(() -> useCase.execute())
        .isInstanceOf(RuntimeException.class)
        .hasMessage("db down");
  }
}