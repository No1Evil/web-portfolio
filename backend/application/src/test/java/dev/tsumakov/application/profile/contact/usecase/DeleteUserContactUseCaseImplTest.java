package dev.tsumakov.application.profile.contact.usecase;

import static org.mockito.Mockito.verify;

import dev.tsumakov.application.profile.contact.port.in.DeleteUserContactUseCase;
import dev.tsumakov.domain.profile.contact.repository.UserContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteUserContactUseCaseImplTest {

  @Mock
  private UserContactRepository repository;

  private DeleteUserContactUseCase useCase;

  @BeforeEach
  void setUp() {
    useCase = new DeleteUserContactUseCaseImpl(repository);
  }

  @Test
  void shouldDeleteContactById() {
    useCase.execute(4);

    verify(repository).delete(4);
  }
}