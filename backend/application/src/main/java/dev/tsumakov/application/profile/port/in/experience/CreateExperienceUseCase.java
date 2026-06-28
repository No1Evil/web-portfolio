package dev.tsumakov.application.profile.port.in.experience;

import dev.tsumakov.application.profile.dto.api.ExperienceDto;
import dev.tsumakov.application.profile.dto.in.CreateExperienceDto;

public interface CreateExperienceUseCase {
  ExperienceDto execute(CreateExperienceDto command);
}
