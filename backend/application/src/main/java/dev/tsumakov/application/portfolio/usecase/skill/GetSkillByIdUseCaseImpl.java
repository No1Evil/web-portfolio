package dev.tsumakov.application.portfolio.usecase.skill;

import dev.tsumakov.application.portfolio.dto.api.SkillDto;
import dev.tsumakov.application.portfolio.mapper.SkillDtoMapper;
import dev.tsumakov.application.portfolio.port.in.skill.GetSkillByIdUseCase;
import dev.tsumakov.domain.portfolio.repository.SkillRepository;
import java.util.Optional;

public class GetSkillByIdUseCaseImpl implements GetSkillByIdUseCase {

  private final SkillRepository skillRepository;
  private final SkillDtoMapper skillDtoMapper;

  public GetSkillByIdUseCaseImpl(SkillRepository skillRepository, SkillDtoMapper skillDtoMapper) {
    this.skillRepository = skillRepository;
    this.skillDtoMapper = skillDtoMapper;
  }

  @Override
  public Optional<SkillDto> execute(Integer id) {
    return skillRepository.findById(id).map(skillDtoMapper::toDto);
  }
}
