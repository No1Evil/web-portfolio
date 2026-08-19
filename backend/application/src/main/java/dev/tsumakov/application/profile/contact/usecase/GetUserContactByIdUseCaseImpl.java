package dev.tsumakov.application.profile.contact.usecase;

import dev.tsumakov.application.profile.contact.dto.outer.UserContactDto;
import dev.tsumakov.application.profile.contact.exception.UserContactNotFoundException;
import dev.tsumakov.application.profile.contact.mapper.UserContactDtoMapper;
import dev.tsumakov.application.profile.contact.port.in.GetUserContactByIdUseCase;
import dev.tsumakov.domain.profile.contact.repository.UserContactRepository;

public class GetUserContactByIdUseCaseImpl implements GetUserContactByIdUseCase {

  private final UserContactRepository repository;
  private final UserContactDtoMapper mapper;

  public GetUserContactByIdUseCaseImpl(UserContactRepository repository,
      UserContactDtoMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public UserContactDto execute(Integer userContactId) {
    var foundContact = repository.findById(userContactId).orElseThrow(() -> new UserContactNotFoundException(
        "User contact with id " + userContactId + " not found"));
    return mapper.toDto(foundContact);
  }
}
