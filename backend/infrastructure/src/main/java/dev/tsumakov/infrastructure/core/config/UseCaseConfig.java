package dev.tsumakov.infrastructure.core.config;

import dev.tsumakov.application.core.mapper.RoleDtoMapper;
import dev.tsumakov.application.core.mapper.UserDtoMapper;
import dev.tsumakov.application.core.port.in.role.CreateRoleUseCase;
import dev.tsumakov.application.core.port.in.role.DeleteRoleUseCase;
import dev.tsumakov.application.core.port.in.role.GetAllRolesUseCase;
import dev.tsumakov.application.core.port.in.user.CreateUserUseCase;
import dev.tsumakov.application.core.port.in.user.GetAllUsersUseCase;
import dev.tsumakov.application.core.port.in.user.GetUserByEmailUseCase;
import dev.tsumakov.application.core.usecase.role.CreateRoleUseCaseImpl;
import dev.tsumakov.application.core.usecase.role.DeleteRoleUseCaseImpl;
import dev.tsumakov.application.core.usecase.role.GetAllRolesUseCaseImpl;
import dev.tsumakov.application.core.usecase.user.CreateUserUseCaseImpl;
import dev.tsumakov.application.core.usecase.user.GetAllUsersUseCaseImpl;
import dev.tsumakov.application.core.usecase.user.GetUserByEmailUseCaseImpl;
import dev.tsumakov.domain.core.repository.RoleRepository;
import dev.tsumakov.domain.core.repository.UserRepository;
import dev.tsumakov.domain.shared.util.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

  @Bean
  public CreateRoleUseCase createRoleUseCase(RoleRepository roleRepository, RoleDtoMapper roleDtoMapper) {
    return new CreateRoleUseCaseImpl(roleRepository, roleDtoMapper);
  }

  @Bean
  public DeleteRoleUseCase deleteRoleUseCase(RoleRepository roleRepository) {
    return new DeleteRoleUseCaseImpl(roleRepository);
  }

  @Bean
  public GetAllRolesUseCase getAllRolesUseCase(RoleRepository roleRepository, RoleDtoMapper roleDtoMapper) {
    return new GetAllRolesUseCaseImpl(roleRepository, roleDtoMapper);
  }

  @Bean
  public CreateUserUseCase createUserUseCase(UserRepository userRepository,
      RoleRepository roleRepository, UserDtoMapper userDtoMapper, PasswordEncoder passwordEncoder)  {
    return new CreateUserUseCaseImpl(userRepository, roleRepository, userDtoMapper, passwordEncoder);
  }

  @Bean
  public GetAllUsersUseCase getAllUsersUseCase(UserRepository userRepository, UserDtoMapper userDtoMapper) {
    return new GetAllUsersUseCaseImpl(userRepository, userDtoMapper);
  }

  @Bean
  public GetUserByEmailUseCase getUserByEmailUseCase(UserRepository userRepository, UserDtoMapper userDtoMapper) {
    return new GetUserByEmailUseCaseImpl(userRepository, userDtoMapper);
  }

}
