package dev.tsumakov.application.profile.usecase.userprofile;

import dev.tsumakov.application.profile.dto.api.UserProfileDto;
import dev.tsumakov.application.profile.mapper.UserProfileDtoMapper;
import dev.tsumakov.application.profile.port.in.userprofile.GetUserProfileByUserIdUseCase;
import dev.tsumakov.domain.profile.repository.UserProfileRepository;
import java.util.Optional;
import java.util.UUID;

public class GetUserProfileByUserIdUseCaseImpl implements GetUserProfileByUserIdUseCase {

  private final UserProfileRepository userProfileRepository;
  private final UserProfileDtoMapper userProfileDtoMapper;

  public GetUserProfileByUserIdUseCaseImpl(UserProfileRepository userProfileRepository, UserProfileDtoMapper userProfileDtoMapper) {
    this.userProfileRepository = userProfileRepository;
    this.userProfileDtoMapper = userProfileDtoMapper;
  }

  @Override
  public Optional<UserProfileDto> execute(UUID userId) {
    return userProfileRepository.findByUserId(userId).map(userProfileDtoMapper::toDto);
  }
}
