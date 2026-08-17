package dev.tsumakov.application.profile.experience.port.in;

import dev.tsumakov.application.profile.experience.dto.in.UpdateUserExperiencePathDto;
import dev.tsumakov.application.profile.experience.dto.outer.UserExperiencePathDto;

public interface UpdateUserExperiencePathUseCase {

  UserExperiencePathDto execute(UpdateUserExperiencePathDto command);

}
