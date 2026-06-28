package dev.tsumakov.application.profile.port.in.education;

import java.util.UUID;

public interface DeleteEducationUseCase {
  void execute(Integer id, UUID userId);
}
