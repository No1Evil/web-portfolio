package dev.tsumakov.application.profile.summary.usecase;

import dev.tsumakov.application.profile.summary.dto.in.UpdateUserSummaryDto;
import dev.tsumakov.application.profile.summary.dto.outer.UserSummaryDto;
import dev.tsumakov.application.profile.summary.exception.UserSummaryNotFoundException;
import dev.tsumakov.application.profile.summary.mapper.UserSummaryDtoMapper;
import dev.tsumakov.application.profile.summary.port.in.UpdateUserSummaryUseCase;
import dev.tsumakov.domain.profile.summary.model.UserSummary;
import dev.tsumakov.domain.profile.summary.repository.UserSummaryRepository;
import java.util.Map;

public class UpdateUserSummaryUseCaseImpl implements UpdateUserSummaryUseCase {

  private final UserSummaryRepository repository;
  private final UserSummaryDtoMapper mapper;

  public UpdateUserSummaryUseCaseImpl(UserSummaryRepository repository, UserSummaryDtoMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public UserSummaryDto execute(UpdateUserSummaryDto command) {
    var currentUserSummary = findById(1);

    var updated = currentUserSummary;
    updated = updateFirstName(updated, command.firstName());
    updated = updateLastName(updated, command.lastName());
    updated = updateProficiency(updated, command.proficiency());
    updated = updateDescription(updated, command.description());
    updated = updateHeroImageUrl(updated, command.heroImageUrl());

    if (!currentUserSummary.equals(updated)) {
      var saved = repository.update(updated.withIncrementedVersion());
      return mapper.toDto(saved);
    }

    return mapper.toDto(currentUserSummary);
  }

  private UserSummary findById(Integer id) {
    return repository.findById(id).orElseThrow(
        () -> new UserSummaryNotFoundException("User summary with id " + id + " not found"));
  }

  private UserSummary updateFirstName(UserSummary summary, String firstName) {
    return firstName == null ? summary : summary.updateFirstName(firstName);
  }

  private UserSummary updateLastName(UserSummary summary, String lastName) {
    return lastName == null ? summary : summary.updateLastName(lastName);
  }

  private UserSummary updateProficiency(UserSummary summary, String proficiency) {
    return proficiency == null ? summary : summary.updateProficiency(proficiency);
  }

  private UserSummary updateDescription(UserSummary summary, Map<String, String> description) {
    return description == null ? summary : summary.updateDescription(description);
  }

  private UserSummary updateHeroImageUrl(UserSummary summary, String heroImageUrl) {
    return heroImageUrl == null ? summary : summary.updateHeroImageUrl(heroImageUrl);
  }
}
