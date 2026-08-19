package dev.tsumakov.application.core.skill.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.core.skill.dto.outer.SkillDto;
import dev.tsumakov.application.core.skill.exception.SkillNotFoundException;
import dev.tsumakov.application.core.skill.mapper.SkillDtoMapper;
import dev.tsumakov.domain.core.skill.model.Skill;
import dev.tsumakov.domain.core.skill.repository.SkillRepository;
import java.time.OffsetDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetSkillByIdUseCaseImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.now();

  @Mock
  private SkillRepository repository;
  @Mock
  private SkillDtoMapper mapper;

  private GetSkillByIdUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new GetSkillByIdUseCaseImpl(repository, mapper);
  }

  @Test
  void shouldReturnSkillDtoWhenFound() {
    var skill = new Skill(1, 1, "Java", "icon.svg", NOW, NOW, 1L);
    var expectedDto = new SkillDto(1, 1, "Java", "icon.svg", NOW, NOW);

    when(repository.findById(1)).thenReturn(Optional.of(skill));
    when(mapper.toDto(skill)).thenReturn(expectedDto);

    var result = useCase.execute(1);

    assertThat(result).isEqualTo(expectedDto);
  }

  @Test
  void shouldThrowNotFoundExceptionWhenSkillMissing() {
    when(repository.findById(99)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> useCase.execute(99))
        .isInstanceOf(SkillNotFoundException.class)
        .hasMessage("Skill with id 99 not found");

    verify(mapper, never()).toDto(org.mockito.ArgumentMatchers.any());
  }
}