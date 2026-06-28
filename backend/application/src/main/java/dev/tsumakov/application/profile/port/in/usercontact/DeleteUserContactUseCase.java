package dev.tsumakov.application.profile.port.in.usercontact;

import java.util.UUID;

public interface DeleteUserContactUseCase {
  void execute(Integer id, UUID userId);
}
