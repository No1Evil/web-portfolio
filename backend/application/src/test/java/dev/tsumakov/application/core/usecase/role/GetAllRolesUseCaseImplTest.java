package dev.tsumakov.application.core.usecase.role;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.core.mapper.RoleDtoMapperImpl;
import dev.tsumakov.domain.core.model.Role;
import dev.tsumakov.domain.core.repository.RoleRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetAllRolesUseCaseImplTest {

  @Mock
  private RoleRepository roleRepository;

  private GetAllRolesUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new GetAllRolesUseCaseImpl(roleRepository, new RoleDtoMapperImpl());
  }

  @Test
  void shouldReturnAllRoles() {
    when(roleRepository.findAll()).thenReturn(List.of(new Role(1, "ADMIN"), new Role(2, "USER")));

    var result = useCase.execute();

    assertThat(result).hasSize(2);
    assertThat(result).extracting("name").containsExactly("ADMIN", "USER");
  }

  @Test
  void shouldReturnEmptyListWhenNoRoles() {
    when(roleRepository.findAll()).thenReturn(List.of());

    var result = useCase.execute();

    assertThat(result).isEmpty();
  }
}
