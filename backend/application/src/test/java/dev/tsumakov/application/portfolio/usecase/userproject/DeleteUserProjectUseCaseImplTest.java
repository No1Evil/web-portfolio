package dev.tsumakov.application.portfolio.usecase.userproject;

import static org.mockito.Mockito.verify;

import dev.tsumakov.domain.portfolio.repository.UserProjectRepository;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteUserProjectUseCaseImplTest {

  @Mock
  private UserProjectRepository userProjectRepository;

  private DeleteUserProjectUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new DeleteUserProjectUseCaseImpl(userProjectRepository);
  }

  @Test
  void shouldDeleteByIdAndUserId() {
    var userId = UUID.randomUUID();

    useCase.execute(1, userId);

    verify(userProjectRepository).deleteByIdAndUserId(1, userId);
  }
}
