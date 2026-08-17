package dev.tsumakov.application.profile.contact.usecase;

import dev.tsumakov.application.profile.contact.dto.in.CreateUserContactDto;
import dev.tsumakov.application.profile.contact.dto.outer.UserContactDto;
import dev.tsumakov.application.profile.contact.mapper.UserContactDtoMapper;
import dev.tsumakov.application.profile.contact.port.in.CreateUserContactUseCase;
import dev.tsumakov.domain.profile.contact.factory.UserContactFactory;
import dev.tsumakov.domain.profile.contact.repository.UserContactRepository;

public class CreateUserContactUseCaseImpl implements CreateUserContactUseCase {

  private final UserContactFactory factory;
  private final UserContactRepository repository;
  private final UserContactDtoMapper mapper;

  public CreateUserContactUseCaseImpl(UserContactFactory factory, UserContactRepository repository, UserContactDtoMapper mapper) {
    this.factory = factory;
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public UserContactDto execute(CreateUserContactDto command) {
    var contact = factory.createNew(command.title(), command.redirectUrl(), command.iconUrl());
    var createdContact = repository.create(contact);
    return mapper.toDto(createdContact);
  }
}
