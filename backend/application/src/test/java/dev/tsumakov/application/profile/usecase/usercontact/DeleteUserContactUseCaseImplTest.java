package dev.tsumakov.application.profile.usecase.usercontact;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.domain.profile.model.UserContact;
import dev.tsumakov.domain.profile.repository.UserContactRepository;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteUserContactUseCaseImplTest {

  @Mock
  private UserContactRepository userContactRepository;

  private DeleteUserContactUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new DeleteUserContactUseCaseImpl(userContactRepository);
  }

  @Test
  void shouldDeleteWhenFoundAndOwned() {
    var userId = UUID.randomUUID();
    var contact = new UserContact(1, userId, Map.of("en", "Email"), Map.of("en", "Work"), null, null);

    when(userContactRepository.findById(1)).thenReturn(Optional.of(contact));

    useCase.execute(1, userId);

    verify(userContactRepository).delete(contact);
  }

  @Test
  void shouldNotDeleteWhenDifferentOwner() {
    var userId = UUID.randomUUID();
    var otherUserId = UUID.randomUUID();
    var contact = new UserContact(1, otherUserId, Map.of("en", "Email"), Map.of("en", "Work"), null, null);

    when(userContactRepository.findById(1)).thenReturn(Optional.of(contact));

    useCase.execute(1, userId);

    verify(userContactRepository).findById(1);
  }

  @Test
  void shouldNotDeleteWhenNotFound() {
    when(userContactRepository.findById(99)).thenReturn(Optional.empty());

    useCase.execute(99, UUID.randomUUID());

    verify(userContactRepository).findById(99);
  }
}
