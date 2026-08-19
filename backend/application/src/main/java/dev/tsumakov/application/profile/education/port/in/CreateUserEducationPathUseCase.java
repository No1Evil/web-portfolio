package dev.tsumakov.application.profile.education.port.in;

import dev.tsumakov.application.profile.education.dto.in.CreateUserEducationPathDto;
import dev.tsumakov.application.profile.education.dto.outer.UserEducationPathDto;

public interface CreateUserEducationPathUseCase {

  UserEducationPathDto execute(CreateUserEducationPathDto command);

}
