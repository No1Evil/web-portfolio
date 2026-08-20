package dev.tsumakov.application.profile.experience.usecase;

import dev.tsumakov.application.profile.experience.dto.in.UpdateUserExperiencePathDto;
import dev.tsumakov.application.profile.experience.dto.outer.UserExperiencePathDto;
import dev.tsumakov.application.profile.experience.exception.UserExperiencePathNotFoundException;
import dev.tsumakov.application.profile.experience.mapper.UserExperiencePathDtoMapper;
import dev.tsumakov.application.profile.experience.port.in.UpdateUserExperiencePathUseCase;
import dev.tsumakov.domain.profile.experience.model.UserExperiencePath;
import dev.tsumakov.domain.profile.experience.repository.UserExperiencePathRepository;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

public class UpdateUserExperiencePathUseCaseImpl implements UpdateUserExperiencePathUseCase {

  private final UserExperiencePathRepository repository;
  private final UserExperiencePathDtoMapper mapper;

  public UpdateUserExperiencePathUseCaseImpl(UserExperiencePathRepository repository,
      UserExperiencePathDtoMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  public UserExperiencePathDto execute(UpdateUserExperiencePathDto command) {
    var currentExperiencePath = findById(command.userExperiencePathId());

    var updated = currentExperiencePath;
    updated = updateTitle(updated, command.title());
    updated = updateCompanyName(updated, command.companyName());
    updated = updateLocation(updated, command.location());
    updated = updateDescription(updated, command.description());
    updated = updateStartDate(updated, command.startDate());
    updated = updateEndDate(updated, command.endDate());
    updated = updatePresent(updated, command.present());

    if (!currentExperiencePath.equals(updated)) {
      var saved = repository.update(updated);
      return mapper.toDto(saved);
    }

    return mapper.toDto(currentExperiencePath);
  }

  private UserExperiencePath findById(UUID userExperiencePathId) {
    return repository.findById(userExperiencePathId).orElseThrow(
        () -> new UserExperiencePathNotFoundException(
            "User experience path with id " + userExperiencePathId + " not found"));
  }

  private UserExperiencePath updateTitle(UserExperiencePath experiencePath, String title) {
    return title == null ? experiencePath : experiencePath.updateTitle(title);
  }

  private UserExperiencePath updateCompanyName(UserExperiencePath experiencePath, String companyName) {
    return companyName == null ? experiencePath : experiencePath.updateCompanyName(companyName);
  }

  private UserExperiencePath updateLocation(UserExperiencePath experiencePath, String location) {
    return location == null ? experiencePath : experiencePath.updateLocation(location);
  }

  private UserExperiencePath updateDescription(UserExperiencePath experiencePath, Map<String, String> description) {
    return description == null ? experiencePath : experiencePath.updateDescription(description);
  }

  private UserExperiencePath updateStartDate(UserExperiencePath experiencePath, OffsetDateTime startDate) {
    return startDate == null ? experiencePath : experiencePath.updateStartDate(startDate);
  }

  private UserExperiencePath updateEndDate(UserExperiencePath experiencePath, OffsetDateTime endDate) {
    return endDate == null ? experiencePath : experiencePath.updateEndDate(endDate);
  }

  private UserExperiencePath updatePresent(UserExperiencePath experiencePath, Boolean present) {
    return present == null ? experiencePath : experiencePath.updatePresent(present);
  }
}
