package dev.tsumakov.application.profile.education.usecase;

import dev.tsumakov.application.profile.education.dto.in.CreateUserEducationPathDto;
import dev.tsumakov.application.profile.education.dto.outer.UserEducationPathDto;
import dev.tsumakov.application.profile.education.mapper.UserEducationPathDtoMapper;
import dev.tsumakov.application.profile.education.port.in.CreateUserEducationPathUseCase;
import dev.tsumakov.domain.profile.education.factory.UserEducationPathFactory;
import dev.tsumakov.domain.profile.education.repository.UserEducationPathRepository;

public class CreateUserEducationPathUseCaseImpl implements CreateUserEducationPathUseCase {

  private final UserEducationPathFactory factory;
  private final UserEducationPathRepository repository;
  private final UserEducationPathDtoMapper mapper;

  public CreateUserEducationPathUseCaseImpl(UserEducationPathFactory factory,
      UserEducationPathRepository repository, UserEducationPathDtoMapper mapper) {
    this.factory = factory;
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public UserEducationPathDto execute(CreateUserEducationPathDto command) {
    var educationPath = factory.createNew(command.title(), command.location(),
        command.description(), command.startDate(), command.endDate(), command.present());
    var createdEducationPath = repository.create(educationPath);
    return mapper.toDto(createdEducationPath);
  }
}
