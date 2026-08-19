package dev.tsumakov.application.profile.education.usecase;

import static org.mockito.Mockito.verify;

import dev.tsumakov.application.profile.education.port.in.DeleteUserEducationPathUseCase;
import dev.tsumakov.domain.profile.education.repository.UserEducationPathRepository;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteUserEducationPathUseCaseImplTest {

  private static final UUID ID = UUID.randomUUID();

  @Mock
  private UserEducationPathRepository repository;

  private DeleteUserEducationPathUseCase useCase;

  @BeforeEach
  void setUp() {
    useCase = new DeleteUserEducationPathUseCaseImpl(repository);
  }

  @Test
  void shouldDeletePathById() {
    useCase.execute(ID);

    verify(repository).delete(ID);
  }
}