package dev.tsumakov.application.profile.experience.usecase;

import dev.tsumakov.application.profile.experience.dto.in.CreateUserExperiencePathDto;
import dev.tsumakov.application.profile.experience.dto.outer.UserExperiencePathDto;
import dev.tsumakov.application.profile.experience.mapper.UserExperiencePathDtoMapper;
import dev.tsumakov.application.profile.experience.port.in.CreateUserExperiencePathUseCase;
import dev.tsumakov.domain.profile.experience.factory.UserExperiencePathFactory;
import dev.tsumakov.domain.profile.experience.repository.UserExperiencePathRepository;

public class CreateUserExperiencePathUseCaseImpl implements CreateUserExperiencePathUseCase {

  private final UserExperiencePathFactory factory;
  private final UserExperiencePathRepository repository;
  private final UserExperiencePathDtoMapper mapper;

  public CreateUserExperiencePathUseCaseImpl(UserExperiencePathFactory factory,
      UserExperiencePathRepository repository,
      UserExperiencePathDtoMapper mapper) {
    this.factory = factory;
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public UserExperiencePathDto execute(CreateUserExperiencePathDto command) {
    var experiencePath = factory.createNew(command.title(), command.companyName(),
        command.location(), command.description(), command.startDate(), command.endDate(), command.present());
    var createdExperiencePath = repository.create(experiencePath);
    return mapper.toDto(createdExperiencePath);
  }
}
