package dev.tsumakov.application.core.skill.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.core.skill.dto.outer.SkillDto;
import dev.tsumakov.application.core.skill.mapper.SkillDtoMapper;
import dev.tsumakov.domain.core.skill.model.Skill;
import dev.tsumakov.domain.core.skill.repository.SkillRepository;
import java.time.OffsetDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetAllSkillsUseCaseImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.now();

  @Mock
  private SkillRepository repository;
  @Mock
  private SkillDtoMapper mapper;

  private GetAllSkillsUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new GetAllSkillsUseCaseImpl(repository, mapper);
  }

  @Test
  void shouldReturnAllSkillsMapped() {
    var java = new Skill(1, 1, "Java", "icon.svg", NOW, NOW, 1L);
    var kotlin = new Skill(2, 1, "Kotlin", null, NOW, NOW, 1L);
    var javaDto = new SkillDto(1, 1, "Java", "icon.svg", NOW, NOW);
    var kotlinDto = new SkillDto(2, 1, "Kotlin", null, NOW, NOW);

    when(repository.findAll()).thenReturn(List.of(java, kotlin));
    when(mapper.toDto(java)).thenReturn(javaDto);
    when(mapper.toDto(kotlin)).thenReturn(kotlinDto);

    var result = useCase.execute();

    assertThat(result).containsExactly(javaDto, kotlinDto);
    verify(mapper).toDto(java);
    verify(mapper).toDto(kotlin);
  }

  @Test
  void shouldReturnEmptyListWhenRepositoryEmpty() {
    when(repository.findAll()).thenReturn(List.of());

    var result = useCase.execute();

    assertThat(result).isEmpty();
    verify(mapper, never()).toDto(org.mockito.ArgumentMatchers.any());
  }
}