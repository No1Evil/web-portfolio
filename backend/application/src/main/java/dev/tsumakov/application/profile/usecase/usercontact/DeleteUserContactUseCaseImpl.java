package dev.tsumakov.application.profile.usecase.usercontact;

import dev.tsumakov.application.profile.port.in.usercontact.DeleteUserContactUseCase;
import dev.tsumakov.domain.profile.repository.UserContactRepository;
import java.util.UUID;

public class DeleteUserContactUseCaseImpl implements DeleteUserContactUseCase {

  private final UserContactRepository userContactRepository;

  public DeleteUserContactUseCaseImpl(UserContactRepository userContactRepository) {
    this.userContactRepository = userContactRepository;
  }

  @Override
  public void execute(Integer id, UUID userId) {
    userContactRepository.findById(id)
        .filter(c -> c.userId().equals(userId))
        .ifPresent(userContactRepository::delete);
  }
}
