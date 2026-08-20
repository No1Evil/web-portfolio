package dev.tsumakov.application.core.user.usecase;

import dev.tsumakov.application.core.user.dto.in.UpdateUserPasswordDto;
import dev.tsumakov.application.core.user.dto.outer.UserDto;
import dev.tsumakov.application.core.user.exception.PasswordValidationException;
import dev.tsumakov.application.core.user.exception.UserNotFoundException;
import dev.tsumakov.application.core.user.mapper.UserDtoMapper;
import dev.tsumakov.application.core.user.port.in.UpdateUserPasswordUseCase;
import dev.tsumakov.domain.core.user.model.User;
import dev.tsumakov.domain.core.user.repository.UserRepository;
import dev.tsumakov.domain.shared.util.PasswordEncoder;

public class UpdateUserPasswordUseCaseImpl implements UpdateUserPasswordUseCase {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository repository;
  private final UserDtoMapper mapper;

  public UpdateUserPasswordUseCaseImpl(PasswordEncoder passwordEncoder, UserRepository repository,
      UserDtoMapper mapper) {
    this.passwordEncoder = passwordEncoder;
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public UserDto execute(UpdateUserPasswordDto command) {
    User user = findUser(command.userId());

    validateOldPassword(command.oldRawPassword(), user.passwordHash());

    String hashedPassword = passwordEncoder.encodePassword(command.rawPassword());
    User updatedUser = user.updatePassword(hashedPassword);

    User savedUser = repository.update(updatedUser);

    return mapper.toDto(savedUser);
  }

  private User findUser(Integer userId) {
    return repository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));
  }

  private void validateOldPassword(String oldRawPassword, String currentPasswordHash) {
    if (!passwordEncoder.matches(oldRawPassword, currentPasswordHash)) {
      throw new PasswordValidationException("Current password does not match");
    }
  }
}
