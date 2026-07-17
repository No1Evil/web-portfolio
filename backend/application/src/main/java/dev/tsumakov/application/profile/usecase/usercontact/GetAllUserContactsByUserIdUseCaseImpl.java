package dev.tsumakov.application.profile.usecase.usercontact;

import dev.tsumakov.application.profile.dto.api.UserContactDto;
import dev.tsumakov.application.profile.mapper.UserContactDtoMapper;
import dev.tsumakov.application.profile.port.in.usercontact.GetAllUserContactsByUserIdUseCase;
import dev.tsumakov.domain.profile.repository.UserContactRepository;
import java.util.List;
import java.util.UUID;

public class GetAllUserContactsByUserIdUseCaseImpl implements GetAllUserContactsByUserIdUseCase {

  private final UserContactRepository userContactRepository;
  private final UserContactDtoMapper userContactDtoMapper;

  public GetAllUserContactsByUserIdUseCaseImpl(UserContactRepository userContactRepository, UserContactDtoMapper userContactDtoMapper) {
    this.userContactRepository = userContactRepository;
    this.userContactDtoMapper = userContactDtoMapper;
  }

  @Override
  public List<UserContactDto> execute(UUID userId) {
    return userContactDtoMapper.toDtoList(userContactRepository.findAllByUserId(userId));
  }
}
