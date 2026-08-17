package dev.tsumakov.application.profile.experience.usecase;

import dev.tsumakov.application.profile.experience.dto.outer.UserExperiencePathDto;
import dev.tsumakov.application.profile.experience.mapper.UserExperiencePathDtoMapper;
import dev.tsumakov.application.profile.experience.port.in.GetAllUserExperiencePathsUseCase;
import dev.tsumakov.domain.profile.experience.repository.UserExperiencePathRepository;
import java.util.List;

public class GetAllUserExperiencePathsUseCaseImpl implements GetAllUserExperiencePathsUseCase {

  private final UserExperiencePathRepository repository;
  private final UserExperiencePathDtoMapper mapper;

  public GetAllUserExperiencePathsUseCaseImpl(UserExperiencePathRepository repository,
      UserExperiencePathDtoMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public List<UserExperiencePathDto> execute() {
    return repository.findAll().stream().map(mapper::toDto).toList();
  }
}
