package dev.tsumakov.application.profile.education.usecase;

import dev.tsumakov.application.profile.education.dto.outer.UserEducationPathDto;
import dev.tsumakov.application.profile.education.mapper.UserEducationPathDtoMapper;
import dev.tsumakov.application.profile.education.port.in.GetAllUserEducationPathsUseCase;
import dev.tsumakov.domain.profile.education.repository.UserEducationPathRepository;
import java.util.List;

public class GetAllUserEducationPathsUseCaseImpl implements GetAllUserEducationPathsUseCase {

  private final UserEducationPathRepository repository;
  private final UserEducationPathDtoMapper mapper;

  public GetAllUserEducationPathsUseCaseImpl(UserEducationPathRepository repository,
      UserEducationPathDtoMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public List<UserEducationPathDto> execute() {
    return repository.findAll().stream().map(mapper::toDto).toList();
  }
}
