package dev.tsumakov.application.profile.experience.usecase;

import static org.mockito.Mockito.verify;

import dev.tsumakov.application.profile.experience.port.in.DeleteUserExperiencePathUseCase;
import dev.tsumakov.domain.profile.experience.repository.UserExperiencePathRepository;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteUserExperiencePathUseCaseImplTest {

  private static final UUID ID = UUID.randomUUID();

  @Mock
  private UserExperiencePathRepository repository;

  private DeleteUserExperiencePathUseCase useCase;

  @BeforeEach
  void setUp() {
    useCase = new DeleteUserExperiencePathUseCaseImpl(repository);
  }

  @Test
  void shouldDeletePathById() {
    useCase.execute(ID);

    verify(repository).delete(ID);
  }
}