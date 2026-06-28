package dev.tsumakov.application.portfolio.usecase.skill;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.portfolio.mapper.SkillDtoMapperImpl;
import dev.tsumakov.domain.portfolio.model.Skill;
import dev.tsumakov.domain.portfolio.repository.SkillRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetAllSkillsUseCaseImplTest {

  @Mock
  private SkillRepository skillRepository;

  private GetAllSkillsUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new GetAllSkillsUseCaseImpl(skillRepository, new SkillDtoMapperImpl());
  }

  @Test
  void shouldReturnAllSkills() {
    when(skillRepository.findAll()).thenReturn(List.of(new Skill(1, 10, "Java"), new Skill(2, 10, "Kotlin")));

    var result = useCase.execute();

    assertThat(result).hasSize(2);
    assertThat(result).extracting("name").containsExactly("Java", "Kotlin");
  }

  @Test
  void shouldReturnEmptyListWhenNoSkills() {
    when(skillRepository.findAll()).thenReturn(List.of());

    var result = useCase.execute();

    assertThat(result).isEmpty();
  }
}
