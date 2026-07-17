package dev.tsumakov.application.profile.usecase.experience;

import dev.tsumakov.application.profile.dto.api.ExperienceDto;
import dev.tsumakov.application.profile.dto.in.CreateExperienceDto;
import dev.tsumakov.application.profile.mapper.ExperienceDtoMapper;
import dev.tsumakov.application.profile.port.in.experience.CreateExperienceUseCase;
import dev.tsumakov.domain.profile.model.Experience;
import dev.tsumakov.domain.profile.repository.ExperienceRepository;

public class CreateExperienceUseCaseImpl implements CreateExperienceUseCase {

  private final ExperienceRepository experienceRepository;
  private final ExperienceDtoMapper experienceDtoMapper;

  public CreateExperienceUseCaseImpl(ExperienceRepository experienceRepository, ExperienceDtoMapper experienceDtoMapper) {
    this.experienceRepository = experienceRepository;
    this.experienceDtoMapper = experienceDtoMapper;
  }

  @Override
  public ExperienceDto execute(CreateExperienceDto command) {
    var experience = new Experience(
        null,
        command.userId(),
        command.company(),
        command.position(),
        command.description(),
        command.startDate(),
        command.endDate()
    );
    experienceRepository.save(experience);
    return experienceDtoMapper.toDto(experience);
  }
}
