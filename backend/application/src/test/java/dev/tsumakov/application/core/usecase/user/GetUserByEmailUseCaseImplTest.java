package dev.tsumakov.application.core.usecase.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.core.mapper.UserDtoMapperImpl;
import dev.tsumakov.domain.core.model.Role;
import dev.tsumakov.domain.core.model.User;
import dev.tsumakov.domain.core.repository.UserRepository;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetUserByEmailUseCaseImplTest {

  @Mock
  private UserRepository userRepository;

  private GetUserByEmailUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new GetUserByEmailUseCaseImpl(userRepository, new UserDtoMapperImpl());
  }

  @Test
  void shouldReturnUserWhenEmailFound() {
    var user = new User(UUID.randomUUID(), "John", "Doe", "john@test.com", null, "secret",
        Set.of(new Role(1, "USER")), null, OffsetDateTime.now(), OffsetDateTime.now());

    when(userRepository.findByEmail("john@test.com")).thenReturn(Optional.of(user));

    var result = useCase.execute("john@test.com");

    assertThat(result).isPresent();
    assertThat(result.get().email()).isEqualTo("john@test.com");
    assertThat(result.get().firstName()).isEqualTo("John");
  }

  @Test
  void shouldReturnEmptyWhenEmailNotFound() {
    when(userRepository.findByEmail("missing@test.com")).thenReturn(Optional.empty());

    var result = useCase.execute("missing@test.com");

    assertThat(result).isEmpty();
  }
}
