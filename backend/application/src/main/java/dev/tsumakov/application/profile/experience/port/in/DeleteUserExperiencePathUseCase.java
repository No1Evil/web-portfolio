package dev.tsumakov.application.profile.experience.port.in;

import java.util.UUID;

public interface DeleteUserExperiencePathUseCase {

  void execute(UUID userExperiencePathId);

}
