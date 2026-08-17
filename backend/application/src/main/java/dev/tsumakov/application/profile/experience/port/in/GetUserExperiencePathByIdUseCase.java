package dev.tsumakov.application.profile.experience.port.in;

import dev.tsumakov.application.profile.experience.dto.outer.UserExperiencePathDto;
import java.util.UUID;

public interface GetUserExperiencePathByIdUseCase {

  UserExperiencePathDto execute(UUID userExperiencePathId);

}
