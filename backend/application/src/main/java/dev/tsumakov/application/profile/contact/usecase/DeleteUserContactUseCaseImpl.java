package dev.tsumakov.application.profile.contact.usecase;

import dev.tsumakov.application.profile.contact.port.in.DeleteUserContactUseCase;
import dev.tsumakov.domain.profile.contact.repository.UserContactRepository;

public class DeleteUserContactUseCaseImpl implements DeleteUserContactUseCase {

  private final UserContactRepository repository;

  public DeleteUserContactUseCaseImpl(UserContactRepository repository) {
    this.repository = repository;
  }

  @Override
  public void execute(Integer userContactId) {
    repository.delete(userContactId);
  }
}
