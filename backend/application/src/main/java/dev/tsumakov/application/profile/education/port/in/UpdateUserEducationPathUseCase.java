package dev.tsumakov.application.profile.education.port.in;

import dev.tsumakov.application.profile.education.dto.in.UpdateUserEducationPathDto;
import dev.tsumakov.application.profile.education.dto.outer.UserEducationPathDto;

public interface UpdateUserEducationPathUseCase {

  UserEducationPathDto execute(UpdateUserEducationPathDto command);

}
