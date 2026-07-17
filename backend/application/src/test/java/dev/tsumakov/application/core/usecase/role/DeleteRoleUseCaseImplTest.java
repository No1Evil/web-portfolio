package dev.tsumakov.application.core.usecase.role;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.domain.core.model.Role;
import dev.tsumakov.domain.core.repository.RoleRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteRoleUseCaseImplTest {

  @Mock
  private RoleRepository roleRepository;

  private DeleteRoleUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new DeleteRoleUseCaseImpl(roleRepository);
  }

  @Test
  void shouldDeleteRoleWhenFound() {
    var role = new Role(1, "MODERATOR");
    when(roleRepository.findByName("MODERATOR")).thenReturn(Optional.of(role));

    useCase.execute("MODERATOR");

    verify(roleRepository).delete(role);
  }

  @Test
  void shouldNotDeleteWhenRoleNotFound() {
    when(roleRepository.findByName("UNKNOWN")).thenReturn(Optional.empty());

    useCase.execute("UNKNOWN");

    verify(roleRepository).findByName("UNKNOWN");
  }
}
