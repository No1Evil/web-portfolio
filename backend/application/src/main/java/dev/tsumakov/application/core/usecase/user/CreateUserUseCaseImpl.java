package dev.tsumakov.application.core.usecase.user;

import dev.tsumakov.application.core.dto.api.UserDto;
import dev.tsumakov.application.core.dto.in.CreateUserDto;
import dev.tsumakov.application.shared.exception.ApplicationException;
import dev.tsumakov.application.core.mapper.UserDtoMapper;
import dev.tsumakov.application.core.port.in.user.CreateUserUseCase;
import dev.tsumakov.domain.core.model.Role;
import dev.tsumakov.domain.core.model.User;
import dev.tsumakov.domain.core.repository.RoleRepository;
import dev.tsumakov.domain.core.repository.UserRepository;
import dev.tsumakov.domain.shared.util.PasswordEncoder;
import io.github.robsonkades.uuidv7.UUIDv7;
import java.time.OffsetDateTime;
import java.util.Set;

public class CreateUserUseCaseImpl implements CreateUserUseCase {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final UserDtoMapper userDtoMapper;
  private final PasswordEncoder passwordEncoder;

  public CreateUserUseCaseImpl(UserRepository userRepository, RoleRepository roleRepository, UserDtoMapper userDtoMapper,
      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.userDtoMapper = userDtoMapper;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserDto execute(CreateUserDto command) {
    var defaultRole = roleRepository.findByName(Role.USER_ROLE_NAME)
        .orElseThrow(() -> new ApplicationException("Default role not found"));

    var encodedPassword = passwordEncoder.encodePassword(command.password());
    var user = createUser(command, encodedPassword, defaultRole);

    userRepository.save(user);
    return userDtoMapper.toDto(user);
  }

  public User createUser(CreateUserDto command, String encodedPassword, Role role) {
    return new User(
        UUIDv7.randomUUID(),
        command.firstName(),
        command.secondName(),
        command.email(),
        command.avatarUrl(),
        encodedPassword,
        Set.of(role),
        Set.of(),
        OffsetDateTime.now(),
        OffsetDateTime.now()
    );
  }
}
