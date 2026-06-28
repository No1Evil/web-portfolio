package dev.tsumakov.application.core.usecase.role;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import dev.tsumakov.application.core.dto.in.CreateRoleDto;
import dev.tsumakov.application.core.mapper.RoleDtoMapperImpl;
import dev.tsumakov.domain.core.model.Role;
import dev.tsumakov.domain.core.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateRoleUseCaseImplTest {

  @Mock
  private RoleRepository roleRepository;

  private CreateRoleUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new CreateRoleUseCaseImpl(roleRepository, new RoleDtoMapperImpl());
  }

  @Test
  void shouldCreateRole() {
    var command = new CreateRoleDto("MODERATOR");

    var result = useCase.execute(command);

    assertThat(result.id()).isNull();
    assertThat(result.name()).isEqualTo("MODERATOR");
    verify(roleRepository).save(any(Role.class));
  }
}
