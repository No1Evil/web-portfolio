package dev.tsumakov.application.profile.education.usecase;

import dev.tsumakov.application.profile.education.dto.outer.UserEducationPathDto;
import dev.tsumakov.application.profile.education.exception.UserEducationPathNotFoundException;
import dev.tsumakov.application.profile.education.mapper.UserEducationPathDtoMapper;
import dev.tsumakov.application.profile.education.port.in.GetUserEducationPathByIdUseCase;
import dev.tsumakov.domain.profile.education.repository.UserEducationPathRepository;
import java.util.UUID;

public class GetUserEducationPathByIdUseCaseImpl implements GetUserEducationPathByIdUseCase {

  private final UserEducationPathRepository repository;
  private final UserEducationPathDtoMapper mapper;

  public GetUserEducationPathByIdUseCaseImpl(UserEducationPathRepository repository,
      UserEducationPathDtoMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public UserEducationPathDto execute(UUID userEducationPathId) {
    var found = repository.findById(userEducationPathId).orElseThrow(
        () -> new UserEducationPathNotFoundException(
            "User education path with id " + userEducationPathId + " not found"));
    return mapper.toDto(found);
  }
}
