package dev.tsumakov.application.profile.usecase.userprofile;

import dev.tsumakov.application.profile.dto.api.UserProfileDto;
import dev.tsumakov.application.profile.dto.in.UpdateUserProfileDto;
import dev.tsumakov.application.profile.mapper.UserProfileDtoMapper;
import dev.tsumakov.application.profile.port.in.userprofile.UpdateUserProfileUseCase;
import dev.tsumakov.domain.profile.model.UserProfile;
import dev.tsumakov.domain.profile.repository.UserProfileRepository;

public class CreateUserProfileUseCaseImpl implements UpdateUserProfileUseCase {

  private final UserProfileRepository userProfileRepository;
  private final UserProfileDtoMapper userProfileDtoMapper;

  public CreateUserProfileUseCaseImpl(UserProfileRepository userProfileRepository, UserProfileDtoMapper userProfileDtoMapper) {
    this.userProfileRepository = userProfileRepository;
    this.userProfileDtoMapper = userProfileDtoMapper;
  }

  @Override
  public UserProfileDto execute(UpdateUserProfileDto command) {
    var profile = UserProfile.createNew(command.userId(), command.title(), command.description());
    userProfileRepository.save(profile);
    return userProfileDtoMapper.toDto(profile);
  }
}
