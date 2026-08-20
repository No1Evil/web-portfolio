package dev.tsumakov.application.profile.summary.usecase;

import dev.tsumakov.application.profile.summary.dto.in.CreateUserSummaryDto;
import dev.tsumakov.application.profile.summary.dto.outer.UserSummaryDto;
import dev.tsumakov.application.profile.summary.exception.UserSummaryAlreadyExistsException;
import dev.tsumakov.application.profile.summary.mapper.UserSummaryDtoMapper;
import dev.tsumakov.application.profile.summary.port.in.CreateUserSummaryUseCase;
import dev.tsumakov.domain.profile.summary.factory.UserSummaryFactory;
import dev.tsumakov.domain.profile.summary.repository.UserSummaryRepository;

public class CreateUserSummaryUseCaseImpl implements CreateUserSummaryUseCase {

  private final UserSummaryRepository repository;
  private final UserSummaryDtoMapper mapper;
  private final UserSummaryFactory factory;

  public CreateUserSummaryUseCaseImpl(UserSummaryRepository repository,
      UserSummaryDtoMapper mapper, UserSummaryFactory factory) {
    this.repository = repository;
    this.mapper = mapper;
    this.factory = factory;
  }

  @Override
  public UserSummaryDto execute(CreateUserSummaryDto command) {
    if (repository.existsById(1)) {
      throw new UserSummaryAlreadyExistsException(
          "User summary with id " + 1 + " already exists");
    }

    var summary = factory.createNew(command.firstName(), command.lastName(), command.proficiency(),
        command.description(),
        command.heroImageUrl());

    var created = repository.create(summary);
    return mapper.toDto(created);
  }
}