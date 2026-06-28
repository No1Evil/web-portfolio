package dev.tsumakov.application.portfolio.usecase.skill;

import static org.mockito.Mockito.verify;

import dev.tsumakov.domain.portfolio.repository.SkillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteSkillUseCaseImplTest {

  @Mock
  private SkillRepository skillRepository;

  private DeleteSkillUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new DeleteSkillUseCaseImpl(skillRepository);
  }

  @Test
  void shouldDeleteById() {
    useCase.execute(1);
    verify(skillRepository).deleteById(1);
  }
}
