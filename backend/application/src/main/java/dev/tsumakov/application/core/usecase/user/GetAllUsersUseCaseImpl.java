package dev.tsumakov.application.core.usecase.user;

import dev.tsumakov.application.core.dto.api.UserDto;
import dev.tsumakov.application.core.mapper.UserDtoMapper;
import dev.tsumakov.application.core.port.in.user.GetAllUsersUseCase;
import dev.tsumakov.domain.core.repository.UserRepository;
import java.util.List;

public class GetAllUsersUseCaseImpl implements GetAllUsersUseCase {

  private final UserRepository userRepository;
  private final UserDtoMapper userDtoMapper;

  public GetAllUsersUseCaseImpl(UserRepository userRepository, UserDtoMapper userDtoMapper) {
    this.userRepository = userRepository;
    this.userDtoMapper = userDtoMapper;
  }

  @Override
  public List<UserDto> execute() {
    return userDtoMapper.toDtoList(userRepository.findAll());
  }
}
