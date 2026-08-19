package dev.tsumakov.application.core.user.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.core.user.dto.in.AuthenticateUserDto;
import dev.tsumakov.application.core.user.dto.outer.UserDto;
import dev.tsumakov.application.core.user.exception.InvalidCredentialsException;
import dev.tsumakov.application.core.user.mapper.UserDtoMapper;
import dev.tsumakov.domain.core.user.model.User;
import dev.tsumakov.domain.core.user.repository.UserRepository;
import dev.tsumakov.domain.shared.util.PasswordEncoder;
import java.time.OffsetDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthenticateUserUseCaseImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.now();
  private static final String DUMMY_HASH =
      "$2a$15$eNW3yDbtrnK8.l1YC2gd/ODX9abTGe/P0V2AYohH7OlkfxngeNZBq";

  @Mock
  private UserRepository repository;
  @Mock
  private PasswordEncoder passwordEncoder;
  @Mock
  private UserDtoMapper mapper;

  private AuthenticateUserUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new AuthenticateUserUseCaseImpl(repository, passwordEncoder, mapper);
  }

  private User currentUser() {
    return new User(1, "admin", "$2a$15$realHash", NOW, NOW, 1L);
  }

  @Test
  void shouldAuthenticateWhenCredentialsAreValid() {
    var command = new AuthenticateUserDto("admin", "password");
    var expected = new UserDto(1, "admin", NOW, NOW);

    when(repository.findByUsername("admin")).thenReturn(Optional.of(currentUser()));
    when(passwordEncoder.matches("password", "$2a$15$realHash")).thenReturn(true);
    when(mapper.toDto(currentUser())).thenReturn(expected);

    var result = useCase.execute(command);

    assertThat(result).isEqualTo(expected);
    verify(passwordEncoder).matches("password", "$2a$15$realHash");
  }

  @Test
  void shouldThrowWhenUsernameIsUnknown() {
    var command = new AuthenticateUserDto("unknown", "password");

    when(repository.findByUsername("unknown")).thenReturn(Optional.empty());
    when(passwordEncoder.matches("password", DUMMY_HASH)).thenReturn(false);

    assertThatThrownBy(() -> useCase.execute(command))
        .isInstanceOf(InvalidCredentialsException.class)
        .hasMessage("Invalid username or password");

    verify(passwordEncoder).matches("password", DUMMY_HASH);
    verify(mapper, never()).toDto(any());
  }

  @Test
  void shouldThrowWhenPasswordDoesNotMatch() {
    var command = new AuthenticateUserDto("admin", "wrong");

    when(repository.findByUsername("admin")).thenReturn(Optional.of(currentUser()));
    when(passwordEncoder.matches("wrong", "$2a$15$realHash")).thenReturn(false);

    assertThatThrownBy(() -> useCase.execute(command))
        .isInstanceOf(InvalidCredentialsException.class)
        .hasMessage("Invalid username or password");

    verify(mapper, never()).toDto(any());
  }
}