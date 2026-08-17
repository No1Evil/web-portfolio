package dev.tsumakov.application.profile.experience.port.in;

import dev.tsumakov.application.profile.education.dto.in.UpdateUserEducationPathDto;
import dev.tsumakov.application.profile.experience.dto.outer.UserExperiencePathDto;

public interface UpdateUserExperiencePathUseCase {

  UserExperiencePathDto execute(UpdateUserEducationPathDto command);

}
