package dev.tsumakov.application.core.usecase.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.core.dto.in.CreateUserDto;
import dev.tsumakov.application.core.mapper.UserDtoMapperImpl;
import dev.tsumakov.application.shared.exception.ApplicationException;
import dev.tsumakov.domain.core.model.Role;
import dev.tsumakov.domain.core.model.User;
import dev.tsumakov.domain.core.repository.RoleRepository;
import dev.tsumakov.domain.core.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseImplTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private RoleRepository roleRepository;

  private CreateUserUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new CreateUserUseCaseImpl(userRepository, roleRepository, new UserDtoMapperImpl(),
        rawPassword -> rawPassword);
  }

  @Test
  void shouldCreateUserWithDefaultRole() {
    var role = new Role(1, Role.USER_ROLE_NAME);
    var command = new CreateUserDto("John", "Doe", "john@test.com", "secret", null);

    when(roleRepository.findByName(Role.USER_ROLE_NAME)).thenReturn(Optional.of(role));

    var result = useCase.execute(command);

    assertThat(result.id()).isNotNull();
    assertThat(result.firstName()).isEqualTo("John");
    assertThat(result.lastName()).isEqualTo("Doe");
    assertThat(result.email()).isEqualTo("john@test.com");
    assertThat(result.avatarUrl()).isNull();
    assertThat(result.roles()).hasSize(1);
    assertThat(result.roles().getFirst().name()).isEqualTo(Role.USER_ROLE_NAME);
    assertThat(result.createdAt()).isNotNull();
    assertThat(result.updatedAt()).isNotNull();
    verify(userRepository).save(any(User.class));
  }

  @Test
  void shouldThrowWhenDefaultRoleNotFound() {
    var command = new CreateUserDto("John", "Doe", "john@test.com", "secret", null);

    when(roleRepository.findByName(Role.USER_ROLE_NAME)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> useCase.execute(command))
        .isInstanceOf(ApplicationException.class)
        .hasMessageContaining("Default role not found");
  }
}
