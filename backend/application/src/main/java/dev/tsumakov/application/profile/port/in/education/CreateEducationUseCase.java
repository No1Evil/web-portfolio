package dev.tsumakov.application.profile.port.in.education;

import dev.tsumakov.application.profile.dto.api.EducationDto;
import dev.tsumakov.application.profile.dto.in.CreateEducationDto;

public interface CreateEducationUseCase {
  EducationDto execute(CreateEducationDto command);
}
