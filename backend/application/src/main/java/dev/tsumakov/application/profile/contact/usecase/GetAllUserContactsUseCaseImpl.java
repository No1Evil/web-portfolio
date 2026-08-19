package dev.tsumakov.application.profile.contact.usecase;

import dev.tsumakov.application.profile.contact.dto.outer.UserContactDto;
import dev.tsumakov.application.profile.contact.mapper.UserContactDtoMapper;
import dev.tsumakov.application.profile.contact.port.in.GetAllUserContactsUseCase;
import dev.tsumakov.domain.profile.contact.repository.UserContactRepository;
import java.util.List;

public class GetAllUserContactsUseCaseImpl implements GetAllUserContactsUseCase {

  private final UserContactRepository repository;
  private final UserContactDtoMapper mapper;

  public GetAllUserContactsUseCaseImpl(UserContactRepository repository,
      UserContactDtoMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public List<UserContactDto> execute() {
    return repository.findAll().stream().map(mapper::toDto).toList();
  }
}
