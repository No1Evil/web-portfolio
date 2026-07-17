package dev.tsumakov.application.profile.usecase.experience;

import dev.tsumakov.application.profile.dto.api.ExperienceDto;
import dev.tsumakov.application.profile.mapper.ExperienceDtoMapper;
import dev.tsumakov.application.profile.port.in.experience.GetAllExperiencesByUserIdUseCase;
import dev.tsumakov.domain.profile.repository.ExperienceRepository;
import java.util.List;
import java.util.UUID;

public class GetAllExperiencesByUserIdUseCaseImpl implements GetAllExperiencesByUserIdUseCase {

  private final ExperienceRepository experienceRepository;
  private final ExperienceDtoMapper experienceDtoMapper;

  public GetAllExperiencesByUserIdUseCaseImpl(ExperienceRepository experienceRepository, ExperienceDtoMapper experienceDtoMapper) {
    this.experienceRepository = experienceRepository;
    this.experienceDtoMapper = experienceDtoMapper;
  }

  @Override
  public List<ExperienceDto> execute(UUID userId) {
    return experienceDtoMapper.toDtoList(experienceRepository.findAllByUserId(userId));
  }
}
