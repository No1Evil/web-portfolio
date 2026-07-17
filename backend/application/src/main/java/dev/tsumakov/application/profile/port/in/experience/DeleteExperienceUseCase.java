package dev.tsumakov.application.profile.port.in.experience;

import java.util.UUID;

public interface DeleteExperienceUseCase {
  void execute(Integer id, UUID userId);
}
