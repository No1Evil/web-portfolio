package dev.tsumakov.application.core.skill.usecase;

import static org.mockito.Mockito.verify;

import dev.tsumakov.application.core.skill.port.in.DeleteSkillUseCase;
import dev.tsumakov.domain.core.skill.repository.SkillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteSkillUseCaseImplTest {

  @Mock
  private SkillRepository repository;

  private DeleteSkillUseCase useCase;

  @BeforeEach
  void setUp() {
    useCase = new DeleteSkillUseCaseImpl(repository);
  }

  @Test
  void shouldDeleteSkillById() {
    useCase.delete(7);

    verify(repository).delete(7);
  }
}