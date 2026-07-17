package dev.tsumakov.application.profile.usecase.education;

import dev.tsumakov.application.profile.dto.api.EducationDto;
import dev.tsumakov.application.profile.mapper.EducationDtoMapper;
import dev.tsumakov.application.profile.port.in.education.GetAllEducationsByUserIdUseCase;
import dev.tsumakov.domain.profile.repository.EducationRepository;
import java.util.List;
import java.util.UUID;

public class GetAllEducationsByUserIdUseCaseImpl implements GetAllEducationsByUserIdUseCase {

  private final EducationRepository educationRepository;
  private final EducationDtoMapper educationDtoMapper;

  public GetAllEducationsByUserIdUseCaseImpl(EducationRepository educationRepository, EducationDtoMapper educationDtoMapper) {
    this.educationRepository = educationRepository;
    this.educationDtoMapper = educationDtoMapper;
  }

  @Override
  public List<EducationDto> execute(UUID userId) {
    return educationDtoMapper.toDtoList(educationRepository.findAllByUserId(userId));
  }
}
