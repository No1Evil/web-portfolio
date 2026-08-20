package dev.tsumakov.application.core.user.usecase;

import dev.tsumakov.application.core.user.dto.outer.UserDto;
import dev.tsumakov.application.core.user.exception.UserNotFoundException;
import dev.tsumakov.application.core.user.mapper.UserDtoMapper;
import dev.tsumakov.application.core.user.port.in.GetCurrentUserUseCase;
import dev.tsumakov.domain.core.user.repository.UserRepository;

public class GetCurrentUserUseCaseImpl implements GetCurrentUserUseCase {

  private final UserRepository repository;
  private final UserDtoMapper mapper;

  public GetCurrentUserUseCaseImpl(UserRepository repository, UserDtoMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public UserDto execute(String username) {
    var user = repository.findByUsername(username).orElseThrow(
        () -> new UserNotFoundException("User " + username + " not found"));
    return mapper.toDto(user);
  }
}