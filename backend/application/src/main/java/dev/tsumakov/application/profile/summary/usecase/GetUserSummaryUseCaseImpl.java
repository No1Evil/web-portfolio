package dev.tsumakov.application.profile.summary.usecase;

import dev.tsumakov.application.profile.summary.dto.outer.UserSummaryDto;
import dev.tsumakov.application.profile.summary.exception.UserSummaryNotFoundException;
import dev.tsumakov.application.profile.summary.mapper.UserSummaryDtoMapper;
import dev.tsumakov.application.profile.summary.port.in.GetUserSummaryUseCase;
import dev.tsumakov.domain.profile.summary.model.UserSummary;
import dev.tsumakov.domain.profile.summary.repository.UserSummaryRepository;

public class GetUserSummaryUseCaseImpl implements GetUserSummaryUseCase {

  private final UserSummaryRepository repository;
  private final UserSummaryDtoMapper mapper;

  public GetUserSummaryUseCaseImpl(UserSummaryRepository repository, UserSummaryDtoMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public UserSummaryDto execute() {
    var userSummary = findById(1);
    return mapper.toDto(userSummary);
  }

  private UserSummary findById(Integer id) {
    return repository.findById(id).orElseThrow(
        () -> new UserSummaryNotFoundException("User summary with id " + id + " not found"));
  }
}
