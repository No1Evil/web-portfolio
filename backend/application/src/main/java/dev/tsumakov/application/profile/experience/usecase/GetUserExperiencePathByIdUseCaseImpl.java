package dev.tsumakov.application.profile.experience.usecase;

import dev.tsumakov.application.profile.experience.dto.outer.UserExperiencePathDto;
import dev.tsumakov.application.profile.experience.exception.UserExperiencePathNotFoundException;
import dev.tsumakov.application.profile.experience.mapper.UserExperiencePathDtoMapper;
import dev.tsumakov.application.profile.experience.port.in.GetUserExperiencePathByIdUseCase;
import dev.tsumakov.domain.profile.experience.repository.UserExperiencePathRepository;
import java.util.UUID;

public class GetUserExperiencePathByIdUseCaseImpl implements GetUserExperiencePathByIdUseCase {

  private final UserExperiencePathRepository repository;
  private final UserExperiencePathDtoMapper mapper;

  public GetUserExperiencePathByIdUseCaseImpl(UserExperiencePathRepository repository,
      UserExperiencePathDtoMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public UserExperiencePathDto execute(UUID userExperiencePathId) {
    var found = repository.findById(userExperiencePathId).orElseThrow(
        () -> new UserExperiencePathNotFoundException(
            "User experience path with id " + userExperiencePathId + " not found"));
    return mapper.toDto(found);
  }
}
