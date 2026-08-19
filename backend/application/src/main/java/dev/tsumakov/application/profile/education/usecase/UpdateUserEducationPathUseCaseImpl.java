package dev.tsumakov.application.profile.education.usecase;

import dev.tsumakov.application.profile.education.dto.in.UpdateUserEducationPathDto;
import dev.tsumakov.application.profile.education.dto.outer.UserEducationPathDto;
import dev.tsumakov.application.profile.education.exception.UserEducationPathNotFoundException;
import dev.tsumakov.application.profile.education.mapper.UserEducationPathDtoMapper;
import dev.tsumakov.application.profile.education.port.in.UpdateUserEducationPathUseCase;
import dev.tsumakov.domain.profile.education.model.UserEducationPath;
import dev.tsumakov.domain.profile.education.repository.UserEducationPathRepository;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

public class UpdateUserEducationPathUseCaseImpl implements UpdateUserEducationPathUseCase {

  private final UserEducationPathRepository repository;
  private final UserEducationPathDtoMapper mapper;

  public UpdateUserEducationPathUseCaseImpl(UserEducationPathRepository repository,
      UserEducationPathDtoMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public UserEducationPathDto execute(UpdateUserEducationPathDto command) {
    var currentEducationPath = findById(command.userEducationPathId());

    var updated = currentEducationPath;
    updated = updateTitle(updated, command.title());
    updated = updateLocation(updated, command.location());
    updated = updateDescription(updated, command.description());
    updated = updateStartDate(updated, command.startDate());
    updated = updateEndDate(updated, command.endDate());
    updated = updatePresent(updated, command.present());

    if (!currentEducationPath.equals(updated)) {
      var saved = repository.update(updated);
      return mapper.toDto(saved);
    }

    return mapper.toDto(currentEducationPath);
  }

  private UserEducationPath findById(UUID id) {
    return repository.findById(id).orElseThrow(
        () -> new UserEducationPathNotFoundException(
            "User education path with id " + id + " not found"));
  }

  private UserEducationPath updateTitle(UserEducationPath path, String title) {
    return title == null ? path : path.updateTitle(title);
  }

  private UserEducationPath updateLocation(UserEducationPath path, String location) {
    return location == null ? path : path.updateLocation(location);
  }

  private UserEducationPath updateDescription(UserEducationPath path, Map<String, String> description) {
    return description == null ? path : path.updateDescription(description);
  }

  private UserEducationPath updateStartDate(UserEducationPath path, OffsetDateTime startDate) {
    return startDate == null ? path : path.updateStartDate(startDate);
  }

  private UserEducationPath updateEndDate(UserEducationPath path, OffsetDateTime endDate) {
    return endDate == null ? path : path.updateEndDate(endDate);
  }

  private UserEducationPath updatePresent(UserEducationPath path, Boolean present) {
    return present == null ? path : path.updatePresent(present);
  }
}
