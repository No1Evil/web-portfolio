package dev.tsumakov.application.profile.usecase.usercontact;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import dev.tsumakov.application.profile.dto.in.CreateUserContactDto;
import dev.tsumakov.application.profile.mapper.UserContactDtoMapperImpl;
import dev.tsumakov.domain.profile.repository.UserContactRepository;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateUserContactUseCaseImplTest {

  @Mock
  private UserContactRepository userContactRepository;

  private CreateUserContactUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new CreateUserContactUseCaseImpl(userContactRepository, new UserContactDtoMapperImpl());
  }

  @Test
  void shouldCreateUserContact() {
    var userId = UUID.randomUUID();
    var command = new CreateUserContactDto(userId, Map.of("en", "Email"), Map.of("en", "Work"),
        "mailto:test@example.com", "icon.svg");

    var result = useCase.execute(command);

    assertThat(result.id()).isNull();
    assertThat(result.userId()).isEqualTo(userId);
    assertThat(result.title()).containsEntry("en", "Email");
    assertThat(result.subtitle()).containsEntry("en", "Work");
    assertThat(result.redirectUrl()).isEqualTo("mailto:test@example.com");
    assertThat(result.iconUrl()).isEqualTo("icon.svg");
    verify(userContactRepository).save(any());
  }
}
