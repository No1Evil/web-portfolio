package dev.tsumakov.application.portfolio.usecase.skill;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.portfolio.mapper.SkillDtoMapperImpl;
import dev.tsumakov.domain.portfolio.model.Skill;
import dev.tsumakov.domain.portfolio.repository.SkillRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetSkillByIdUseCaseImplTest {

  @Mock
  private SkillRepository skillRepository;

  private GetSkillByIdUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new GetSkillByIdUseCaseImpl(skillRepository, new SkillDtoMapperImpl());
  }

  @Test
  void shouldReturnSkillWhenFound() {
    when(skillRepository.findById(1)).thenReturn(Optional.of(new Skill(1, 10, "Java")));

    var result = useCase.execute(1);

    assertThat(result).isPresent();
    assertThat(result.get().name()).isEqualTo("Java");
  }

  @Test
  void shouldReturnEmptyWhenNotFound() {
    when(skillRepository.findById(99)).thenReturn(Optional.empty());

    var result = useCase.execute(99);

    assertThat(result).isEmpty();
  }
}
