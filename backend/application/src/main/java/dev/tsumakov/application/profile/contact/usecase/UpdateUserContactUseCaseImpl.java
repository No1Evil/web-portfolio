package dev.tsumakov.application.profile.contact.usecase;

import dev.tsumakov.application.profile.contact.dto.in.UpdateUserContactDto;
import dev.tsumakov.application.profile.contact.dto.outer.UserContactDto;
import dev.tsumakov.application.profile.contact.exception.UserContactNotFoundException;
import dev.tsumakov.application.profile.contact.mapper.UserContactDtoMapper;
import dev.tsumakov.application.profile.contact.port.in.UpdateUserContactUseCase;
import dev.tsumakov.domain.profile.contact.model.UserContact;
import dev.tsumakov.domain.profile.contact.repository.UserContactRepository;

public class UpdateUserContactUseCaseImpl implements UpdateUserContactUseCase {

  private final UserContactRepository repository;
  private final UserContactDtoMapper mapper;

  public UpdateUserContactUseCaseImpl(UserContactRepository repository,
      UserContactDtoMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public UserContactDto execute(UpdateUserContactDto command) {
    var currentContact = findUserContact(command.userContactId());

    var updatedContact = currentContact;
    updatedContact = updateTitle(updatedContact, command.title());
    updatedContact = updateRedirectUrl(updatedContact, command.redirectUrl());
    updatedContact = updateIconUrl(updatedContact, command.iconUrl());

    if (!currentContact.equals(updatedContact)) {
      var savedContact = repository.update(updatedContact.withIncrementedVersion());
      return mapper.toDto(savedContact);
    }

    return mapper.toDto(currentContact);
  }

  private UserContact findUserContact(Integer userContactId) {
    return repository.findById(userContactId).orElseThrow(() -> new UserContactNotFoundException(
        "User contact with id " + userContactId + " not found"));
  }

  private UserContact updateTitle(UserContact contact, String title) {
    return title == null ? contact : contact.updateTitle(title);
  }

  private UserContact updateRedirectUrl(UserContact contact, String redirectUrl) {
    return redirectUrl == null ? contact : contact.updateRedirectUrl(redirectUrl);
  }

  private UserContact updateIconUrl(UserContact contact, String iconUrl) {
    return iconUrl == null ? contact : contact.updateIconUrl(iconUrl);
  }
}
