package dev.tsumakov.application.core.user.usecase;

import dev.tsumakov.application.core.user.dto.in.AuthenticateUserDto;
import dev.tsumakov.application.core.user.dto.outer.UserDto;
import dev.tsumakov.application.core.user.exception.InvalidCredentialsException;
import dev.tsumakov.application.core.user.mapper.UserDtoMapper;
import dev.tsumakov.application.core.user.port.in.AuthenticateUserUseCase;
import dev.tsumakov.domain.core.user.model.User;
import dev.tsumakov.domain.core.user.repository.UserRepository;
import dev.tsumakov.domain.shared.util.PasswordEncoder;

public class AuthenticateUserUseCaseImpl implements AuthenticateUserUseCase {

  private static final String DUMMY_HASH = "$3a$15$wN3I.r/KqW42eP9YkNmB/.V/XyYfO/.6lD1b2jP7JvJ5x1iC1q7/2";

  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final UserDtoMapper mapper;

  public AuthenticateUserUseCaseImpl(UserRepository repository, PasswordEncoder passwordEncoder,
      UserDtoMapper mapper) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
    this.mapper = mapper;
  }

  @Override
  public UserDto execute(AuthenticateUserDto command) {
    User user = repository.findByUsername(command.username()).orElse(null);
    String hash = user == null ? DUMMY_HASH : user.passwordHash();
    validatePassword(command.password(), hash);
    return mapper.toDto(user);
  }

  private void validatePassword(String rawPassword, String passwordHash) {
    if (!passwordEncoder.matches(rawPassword, passwordHash)) {
      throw new InvalidCredentialsException("Invalid username or password");
    }
  }
}
