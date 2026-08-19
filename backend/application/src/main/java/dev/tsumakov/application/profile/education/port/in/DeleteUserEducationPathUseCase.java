package dev.tsumakov.application.profile.education.port.in;

import java.util.UUID;

public interface DeleteUserEducationPathUseCase {

  void execute(UUID userEducationPathId);

}
