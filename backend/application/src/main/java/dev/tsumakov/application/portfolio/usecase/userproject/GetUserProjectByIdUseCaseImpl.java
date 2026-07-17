package dev.tsumakov.application.portfolio.usecase.userproject;

import dev.tsumakov.application.portfolio.dto.api.UserProjectDto;
import dev.tsumakov.application.portfolio.mapper.UserProjectDtoMapper;
import dev.tsumakov.application.portfolio.port.in.userproject.GetUserProjectByIdUseCase;
import dev.tsumakov.domain.portfolio.repository.UserProjectRepository;
import java.util.Optional;
import java.util.UUID;

public class GetUserProjectByIdUseCaseImpl implements GetUserProjectByIdUseCase {

  private final UserProjectRepository userProjectRepository;
  private final UserProjectDtoMapper userProjectDtoMapper;

  public GetUserProjectByIdUseCaseImpl(
      UserProjectRepository userProjectRepository,
      UserProjectDtoMapper userProjectDtoMapper
  ) {
    this.userProjectRepository = userProjectRepository;
    this.userProjectDtoMapper = userProjectDtoMapper;
  }

  @Override
  public Optional<UserProjectDto> execute(Integer projectId, UUID userId) {
    return userProjectRepository.findByIdAndUserId(projectId, userId).stream()
        .findFirst()
        .map(userProjectDtoMapper::toDto);
  }
}
