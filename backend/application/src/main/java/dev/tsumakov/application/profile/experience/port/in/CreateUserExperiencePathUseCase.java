package dev.tsumakov.application.profile.experience.port.in;

import dev.tsumakov.application.profile.experience.dto.in.CreateUserExperiencePathDto;
import dev.tsumakov.application.profile.experience.dto.outer.UserExperiencePathDto;

public interface CreateUserExperiencePathUseCase {

  UserExperiencePathDto execute(CreateUserExperiencePathDto command);

}
