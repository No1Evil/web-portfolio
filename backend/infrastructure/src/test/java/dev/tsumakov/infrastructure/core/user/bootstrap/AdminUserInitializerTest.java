package dev.tsumakov.infrastructure.core.user.bootstrap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

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
class AdminUserInitializerTest {

  private static final String USERNAME = "admin";
  private static final String RAW_PASSWORD = "secret";
  private static final String HASH = "$2a$10$fixed-hash";
  private static final OffsetDateTime CREATED_AT = OffsetDateTime.parse("2024-01-01T10:00:00Z");
  private static final OffsetDateTime UPDATED_AT = OffsetDateTime.parse("2024-06-01T10:00:00Z");

  @Mock
  private UserRepository repository;
  @Mock
  private PasswordEncoder passwordEncoder;

  private AdminUserInitializer initializer;

  @BeforeEach
  void setUp() {
    initializer = new AdminUserInitializer(repository, passwordEncoder, USERNAME, RAW_PASSWORD);
  }

  @Test
  void shouldCreateAdminWhenAbsent() {
    when(passwordEncoder.encodePassword(RAW_PASSWORD)).thenReturn(HASH);
    when(repository.findById(1)).thenReturn(Optional.empty());

    initializer.run(null);

    var captor = ArgumentCaptor.forClass(User.class);
    verify(repository).create(captor.capture());
    verify(repository, never()).update(any());
    var created = captor.getValue();
    assertThat(created.id()).isEqualTo(1);
    assertThat(created.username()).isEqualTo(USERNAME);
    assertThat(created.passwordHash()).isEqualTo(HASH);
  }

  @Test
  void shouldUpdateAdminWithNewCredentialsWhenPresent() {
    var existing = new User(1, "old-admin", "$2a$10$old-hash", CREATED_AT, UPDATED_AT, 3L);
    when(passwordEncoder.encodePassword(RAW_PASSWORD)).thenReturn(HASH);
    when(repository.findById(1)).thenReturn(Optional.of(existing));

    initializer.run(null);

    var captor = ArgumentCaptor.forClass(User.class);
    verify(repository).update(captor.capture());
    verify(repository, never()).create(any());
    var updated = captor.getValue();
    assertThat(updated.id()).isEqualTo(1);
    assertThat(updated.username()).isEqualTo(USERNAME);
    assertThat(updated.passwordHash()).isEqualTo(HASH);
    assertThat(updated.createdAt()).isEqualTo(CREATED_AT);
    assertThat(updated.updatedAt()).isEqualTo(UPDATED_AT);
    assertThat(updated.version()).isEqualTo(3L);
  }

  @Test
  void shouldNotWriteAnythingWhenAdminAlreadyUpToDate() {
    var existing = new User(1, USERNAME, HASH, CREATED_AT, UPDATED_AT, 3L);
    when(repository.findById(1)).thenReturn(Optional.of(existing));
    when(passwordEncoder.matches(RAW_PASSWORD, HASH)).thenReturn(true);

    initializer.run(null);

    verify(repository, never()).create(any());
    verify(repository, never()).update(any());
    verify(passwordEncoder, never()).encodePassword(any());
  }

  @Test
  void shouldFailWhenUsernameBlank() {
    var blankUsername = new AdminUserInitializer(repository, passwordEncoder, "  ", RAW_PASSWORD);

    assertThatThrownBy(() -> blankUsername.run(null))
        .isInstanceOf(IllegalStateException.class)
        .hasMessageContaining("ADMIN_USERNAME");

    verifyNoInteractions(repository);
  }

  @Test
  void shouldFailWhenPasswordNull() {
    var nullPassword = new AdminUserInitializer(repository, passwordEncoder, USERNAME, null);

    assertThatThrownBy(() -> nullPassword.run(null))
        .isInstanceOf(IllegalStateException.class)
        .hasMessageContaining("ADMIN_PASSWORD");

    verifyNoInteractions(repository);
  }
}
