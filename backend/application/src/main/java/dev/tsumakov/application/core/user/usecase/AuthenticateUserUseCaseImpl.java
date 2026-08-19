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

  private static final String DUMMY_HASH = "$2a$15$eNW3yDbtrnK8.l1YC2gd/ODX9abTGe/P0V2AYohH7OlkfxngeNZBq";

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
    if (user == null ) {
      validatePassword(command.password(), DUMMY_HASH);
      throw new InvalidCredentialsException("Invalid username or password");
    } else {
      validatePassword(command.password(), user.passwordHash());
    }
    return mapper.toDto(user);
  }

  private void validatePassword(String rawPassword, String passwordHash) {
    if (!passwordEncoder.matches(rawPassword, passwordHash)) {
      throw new InvalidCredentialsException("Invalid username or password");
    }
  }
}
