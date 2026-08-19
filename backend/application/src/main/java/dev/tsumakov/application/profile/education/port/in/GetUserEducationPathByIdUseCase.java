package dev.tsumakov.application.profile.education.port.in;

import dev.tsumakov.application.profile.education.dto.outer.UserEducationPathDto;
import java.util.UUID;

public interface GetUserEducationPathByIdUseCase {

  UserEducationPathDto execute(UUID userEducationPathId);

}
