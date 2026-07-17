package dev.tsumakov.application.profile.usecase.usercontact;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.profile.mapper.UserContactDtoMapperImpl;
import dev.tsumakov.domain.profile.model.UserContact;
import dev.tsumakov.domain.profile.repository.UserContactRepository;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetAllUserContactsByUserIdUseCaseImplTest {

  @Mock
  private UserContactRepository userContactRepository;

  private GetAllUserContactsByUserIdUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new GetAllUserContactsByUserIdUseCaseImpl(userContactRepository,
        new UserContactDtoMapperImpl());
  }

  @Test
  void shouldReturnContactsForUser() {
    var userId = UUID.randomUUID();
    var contacts = List.of(
        new UserContact(1, userId, Map.of("en", "Email"), Map.of("en", "Work"), null, null),
        new UserContact(2, userId, Map.of("en", "Phone"), Map.of("en", "Mobile"), null, null)
    );

    when(userContactRepository.findAllByUserId(userId)).thenReturn(contacts);

    var result = useCase.execute(userId);

    assertThat(result).hasSize(2);
    assertThat(result).extracting("title")
        .extracting(t -> ((Map<String, String>) t).get("en"))
        .containsExactly("Email", "Phone");
  }

  @Test
  void shouldReturnEmptyListWhenNoContacts() {
    var userId = UUID.randomUUID();

    when(userContactRepository.findAllByUserId(userId)).thenReturn(List.of());

    var result = useCase.execute(userId);

    assertThat(result).isEmpty();
  }
}
