package dev.tsumakov.application.profile.usecase.education;

import dev.tsumakov.application.profile.port.in.education.DeleteEducationUseCase;
import dev.tsumakov.domain.profile.repository.EducationRepository;
import java.util.UUID;

public class DeleteEducationUseCaseImpl implements DeleteEducationUseCase {

  private final EducationRepository educationRepository;

  public DeleteEducationUseCaseImpl(EducationRepository educationRepository) {
    this.educationRepository = educationRepository;
  }

  @Override
  public void execute(Integer id, UUID userId) {
    educationRepository.findById(id)
        .filter(e -> e.userId().equals(userId))
        .ifPresent(educationRepository::delete);
  }
}
