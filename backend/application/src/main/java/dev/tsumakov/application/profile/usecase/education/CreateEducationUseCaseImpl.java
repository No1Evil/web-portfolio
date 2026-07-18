package dev.tsumakov.application.profile.usecase.education;

import dev.tsumakov.application.profile.dto.api.EducationDto;
import dev.tsumakov.application.profile.dto.in.CreateEducationDto;
import dev.tsumakov.application.profile.mapper.EducationDtoMapper;
import dev.tsumakov.application.profile.port.in.education.CreateEducationUseCase;
import dev.tsumakov.domain.profile.model.Education;
import dev.tsumakov.domain.profile.repository.EducationRepository;

public class CreateEducationUseCaseImpl implements CreateEducationUseCase {

  private final EducationRepository educationRepository;
  private final EducationDtoMapper educationDtoMapper;

  public CreateEducationUseCaseImpl(EducationRepository educationRepository, EducationDtoMapper educationDtoMapper) {
    this.educationRepository = educationRepository;
    this.educationDtoMapper = educationDtoMapper;
  }

  @Override
  public EducationDto execute(CreateEducationDto command) {
    var education = createEducation(command);
    educationRepository.save(education);
    return educationDtoMapper.toDto(education);
  }

  private Education createEducation(CreateEducationDto command) {
    return Education.createNew(
        command.userId(),
        command.institution(),
        command.degree(),
        command.startDate(),
        command.endDate()
    );
  }
}
