package dev.tsumakov.application.profile.usecase.experience;

import dev.tsumakov.application.profile.port.in.experience.DeleteExperienceUseCase;
import dev.tsumakov.domain.profile.repository.ExperienceRepository;
import java.util.UUID;

public class DeleteExperienceUseCaseImpl implements DeleteExperienceUseCase {

  private final ExperienceRepository experienceRepository;

  public DeleteExperienceUseCaseImpl(ExperienceRepository experienceRepository) {
    this.experienceRepository = experienceRepository;
  }

  @Override
  public void execute(Integer id, UUID userId) {
    experienceRepository.findById(id)
        .filter(e -> e.userId().equals(userId))
        .ifPresent(experienceRepository::delete);
  }
}
