package dev.tsumakov.application.profile.experience.port.in;

import dev.tsumakov.application.profile.experience.dto.outer.UserExperiencePathDto;
import java.util.List;

public interface GetAllUserExperiencePathsUseCase {

  List<UserExperiencePathDto> execute();

}
