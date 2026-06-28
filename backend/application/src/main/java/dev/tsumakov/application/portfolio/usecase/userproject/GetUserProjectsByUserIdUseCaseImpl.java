package dev.tsumakov.application.portfolio.usecase.userproject;

import dev.tsumakov.application.portfolio.dto.api.UserProjectDto;
import dev.tsumakov.application.portfolio.mapper.UserProjectDtoMapper;
import dev.tsumakov.application.portfolio.port.in.userproject.GetUserProjectsByUserIdUseCase;
import dev.tsumakov.domain.portfolio.repository.UserProjectRepository;
import java.util.List;
import java.util.UUID;

public class GetUserProjectsByUserIdUseCaseImpl implements GetUserProjectsByUserIdUseCase {

  private final UserProjectRepository userProjectRepository;
  private final UserProjectDtoMapper userProjectDtoMapper;

  public GetUserProjectsByUserIdUseCaseImpl(
      UserProjectRepository userProjectRepository,
      UserProjectDtoMapper userProjectDtoMapper
  ) {
    this.userProjectRepository = userProjectRepository;
    this.userProjectDtoMapper = userProjectDtoMapper;
  }

  @Override
  public List<UserProjectDto> execute(UUID userId) {
    return userProjectDtoMapper.toDtoList(userProjectRepository.findAllByUserId(userId));
  }
}
