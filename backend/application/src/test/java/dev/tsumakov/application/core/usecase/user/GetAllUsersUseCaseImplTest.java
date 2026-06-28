package dev.tsumakov.application.core.usecase.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.core.mapper.UserDtoMapperImpl;
import dev.tsumakov.domain.core.model.Role;
import dev.tsumakov.domain.core.model.User;
import dev.tsumakov.domain.core.repository.UserRepository;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetAllUsersUseCaseImplTest {

  @Mock
  private UserRepository userRepository;

  private GetAllUsersUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new GetAllUsersUseCaseImpl(userRepository, new UserDtoMapperImpl());
  }

  @Test
  void shouldReturnAllUsers() {
    var users = List.of(
        new User(UUID.randomUUID(), "John", "Doe", "john@test.com", null, "secret",
            Set.of(new Role(1, "USER")), null, OffsetDateTime.now(), OffsetDateTime.now()),
        new User(UUID.randomUUID(), "Jane", "Doe", "jane@test.com", null, "secret",
            Set.of(new Role(1, "ADMIN")), null, OffsetDateTime.now(), OffsetDateTime.now())
    );

    when(userRepository.findAll()).thenReturn(users);

    var result = useCase.execute();

    assertThat(result).hasSize(2);
    assertThat(result).extracting("firstName").containsExactly("John", "Jane");
  }

  @Test
  void shouldReturnEmptyListWhenNoUsers() {
    when(userRepository.findAll()).thenReturn(List.of());

    var result = useCase.execute();

    assertThat(result).isEmpty();
  }
}
