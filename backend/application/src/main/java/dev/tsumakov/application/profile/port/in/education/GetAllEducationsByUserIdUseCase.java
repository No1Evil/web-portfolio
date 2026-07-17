package dev.tsumakov.application.profile.port.in.education;

import dev.tsumakov.application.profile.dto.api.EducationDto;
import java.util.List;
import java.util.UUID;

public interface GetAllEducationsByUserIdUseCase {
  List<EducationDto> execute(UUID userId);
}
