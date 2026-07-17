package dev.tsumakov.application.portfolio.usecase.skill;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import dev.tsumakov.application.portfolio.dto.in.CreateSkillDto;
import dev.tsumakov.application.portfolio.mapper.SkillDtoMapperImpl;
import dev.tsumakov.domain.portfolio.repository.SkillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateSkillUseCaseImplTest {

  @Mock
  private SkillRepository skillRepository;

  private CreateSkillUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new CreateSkillUseCaseImpl(skillRepository, new SkillDtoMapperImpl());
  }

  @Test
  void shouldCreateSkill() {
    var command = new CreateSkillDto(10, "Java");

    var result = useCase.execute(command);

    assertThat(result.id()).isNull();
    assertThat(result.categoryId()).isEqualTo(10);
    assertThat(result.name()).isEqualTo("Java");
    verify(skillRepository).save(any());
  }
}
