package dev.tsumakov.application.profile.port.in.experience;

import dev.tsumakov.application.profile.dto.api.ExperienceDto;
import java.util.List;
import java.util.UUID;

public interface GetAllExperiencesByUserIdUseCase {
  List<ExperienceDto> execute(UUID userId);
}
