package dev.tsumakov.application.core.user.usecase;

import dev.tsumakov.application.core.user.dto.in.AuthenticateUserDto;
import dev.tsumakov.application.core.user.dto.outer.UserDto;
import dev.tsumakov.application.core.user.exception.InvalidCredentialsException;
import dev.tsumakov.application.core.user.mapper.UserDtoMapper;
import dev.tsumakov.application.core.user.port.in.AuthenticateUserUseCase;
import dev.tsumakov.domain.core.user.model.User;
import dev.tsumakov.domain.core.user.repository.UserRepository;
import dev.tsumakov.domain.shared.util.PasswordEncoder;
import java.util.concurrent.ThreadLocalRandom;

public class AuthenticateUserUseCaseImpl implements AuthenticateUserUseCase {

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
    validateUserAndPassword(user, command.password());
    return mapper.toDto(user);
  }

  /**
   * On wrong username or password sets Thread to sleep in period of 200 to 500ms.
   * Maybe better would be the dummy hash
   */
  private void validateUserAndPassword(User user, String rawPassword) {
    if (user == null || !passwordEncoder.matches(rawPassword, user.passwordHash())) {
      applyRandomDelay(200, 500);
      throw new InvalidCredentialsException("Invalid username or password");
    }
  }

  private void applyRandomDelay(int minMs, int maxMs) {
    try {
      long delay = ThreadLocalRandom.current().nextLong(minMs, maxMs + 1);
      Thread.sleep(delay);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
