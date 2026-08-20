package dev.tsumakov.application.core.user.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.core.user.dto.in.UpdateUserPasswordDto;
import dev.tsumakov.application.core.user.dto.outer.UserDto;
import dev.tsumakov.application.core.user.exception.PasswordValidationException;
import dev.tsumakov.application.core.user.exception.UserNotFoundException;
import dev.tsumakov.application.core.user.mapper.UserDtoMapper;
import dev.tsumakov.domain.core.user.model.User;
import dev.tsumakov.domain.core.user.repository.UserRepository;
import dev.tsumakov.domain.shared.util.PasswordEncoder;
import java.time.OffsetDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateUserPasswordUseCaseImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.now();

  @Mock
  private PasswordEncoder passwordEncoder;
  @Mock
  private UserRepository repository;
  @Mock
  private UserDtoMapper mapper;

  private UpdateUserPasswordUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new UpdateUserPasswordUseCaseImpl(passwordEncoder, repository, mapper);
  }

  private User currentUser() {
    return new User(1, "admin", "$2a$10$oldHash", NOW, NOW, 4L);
  }

  @Test
  void shouldUpdatePasswordWhenOldPasswordMatches() {
    var command = new UpdateUserPasswordDto(1, "oldPass", "newPass");
    var saved = new User(1, "admin", "$2a$10$newHash", NOW, NOW, 4L);
    var expectedDto = new UserDto(1, "admin", NOW, NOW);

    when(repository.findById(1)).thenReturn(Optional.of(currentUser()));
    when(passwordEncoder.matches("oldPass", "$2a$10$oldHash")).thenReturn(true);
    when(passwordEncoder.encodePassword("newPass")).thenReturn("$2a$10$newHash");
    when(repository.update(any())).thenReturn(saved);
    when(mapper.toDto(saved)).thenReturn(expectedDto);

    var result = useCase.execute(command);

    assertThat(result).isEqualTo(expectedDto);
    verify(passwordEncoder).matches("oldPass", "$2a$10$oldHash");
    verify(passwordEncoder).encodePassword("newPass");

    ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
    verify(repository).update(captor.capture());
    var updated = captor.getValue();
    assertThat(updated.passwordHash()).isEqualTo("$2a$10$newHash");
    assertThat(updated.version()).isEqualTo(4L);
    assertThat(updated.username()).isEqualTo("admin");
  }

  @Test
  void shouldThrowNotFoundExceptionWhenUserMissing() {
    var command = new UpdateUserPasswordDto(99, "oldPass", "newPass");

    when(repository.findById(99)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> useCase.execute(command))
        .isInstanceOf(UserNotFoundException.class)
        .hasMessage("User with id 99 not found");

    verify(passwordEncoder, never()).encodePassword(any());
    verify(repository, never()).update(any());
  }

  @Test
  void shouldThrowWhenOldPasswordDoesNotMatch() {
    var command = new UpdateUserPasswordDto(1, "wrongPass", "newPass");

    when(repository.findById(1)).thenReturn(Optional.of(currentUser()));
    when(passwordEncoder.matches("wrongPass", "$2a$10$oldHash")).thenReturn(false);

    assertThatThrownBy(() -> useCase.execute(command))
        .isInstanceOf(PasswordValidationException.class)
        .hasMessage("Current password does not match");

    verify(passwordEncoder, never()).encodePassword(any());
    verify(repository, never()).update(any());
  }
}