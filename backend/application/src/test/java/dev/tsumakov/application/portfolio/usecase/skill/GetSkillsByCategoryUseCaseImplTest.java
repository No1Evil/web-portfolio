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
class GetSkillsByCategoryUseCaseImplTest {

  @Mock
  private SkillRepository skillRepository;

  private GetSkillsByCategoryUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new GetSkillsByCategoryUseCaseImpl(skillRepository, new SkillDtoMapperImpl());
  }

  @Test
  void shouldReturnSkillsForCategory() {
    when(skillRepository.findAllByCategory(10))
        .thenReturn(List.of(new Skill(1, 10, "Java"), new Skill(2, 10, "Kotlin")));

    var result = useCase.execute(10);

    assertThat(result).hasSize(2);
    assertThat(result).extracting("name").containsExactly("Java", "Kotlin");
  }

  @Test
  void shouldReturnEmptyListWhenNoSkillsForCategory() {
    when(skillRepository.findAllByCategory(99)).thenReturn(List.of());

    var result = useCase.execute(99);

    assertThat(result).isEmpty();
  }
}
