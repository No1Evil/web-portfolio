package dev.tsumakov.application.profile.usecase.usercontact;

import dev.tsumakov.application.profile.dto.api.UserContactDto;
import dev.tsumakov.application.profile.dto.in.CreateUserContactDto;
import dev.tsumakov.application.profile.mapper.UserContactDtoMapper;
import dev.tsumakov.application.profile.port.in.usercontact.CreateUserContactUseCase;
import dev.tsumakov.domain.profile.model.UserContact;
import dev.tsumakov.domain.profile.repository.UserContactRepository;

public class CreateUserContactUseCaseImpl implements CreateUserContactUseCase {

  private final UserContactRepository userContactRepository;
  private final UserContactDtoMapper userContactDtoMapper;

  public CreateUserContactUseCaseImpl(UserContactRepository userContactRepository, UserContactDtoMapper userContactDtoMapper) {
    this.userContactRepository = userContactRepository;
    this.userContactDtoMapper = userContactDtoMapper;
  }

  @Override
  public UserContactDto execute(CreateUserContactDto command) {
    var contact = new UserContact(
        null,
        command.userId(),
        command.title(),
        command.subtitle(),
        command.redirectUrl(),
        command.iconUrl()
    );
    userContactRepository.save(contact);
    return userContactDtoMapper.toDto(contact);
  }
}
