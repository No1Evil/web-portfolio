package dev.tsumakov.application.core.usecase.user;

import dev.tsumakov.application.core.dto.api.UserDto;
import dev.tsumakov.application.core.mapper.UserDtoMapper;
import dev.tsumakov.application.core.port.in.user.GetUserByEmailUseCase;
import dev.tsumakov.domain.core.repository.UserRepository;
import java.util.Optional;

public class GetUserByEmailUseCaseImpl implements GetUserByEmailUseCase {

  private final UserRepository userRepository;
  private final UserDtoMapper userDtoMapper;

  public GetUserByEmailUseCaseImpl(UserRepository userRepository, UserDtoMapper userDtoMapper) {
    this.userRepository = userRepository;
    this.userDtoMapper = userDtoMapper;
  }

  @Override
  public Optional<UserDto> execute(String email) {
    return userRepository.findByEmail(email).map(userDtoMapper::toDto);
  }
}
