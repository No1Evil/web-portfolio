package dev.tsumakov.application.core.skill.usecase;

import dev.tsumakov.application.core.skill.dto.outer.SkillDto;
import dev.tsumakov.application.core.skill.exception.SkillNotFoundException;
import dev.tsumakov.application.core.skill.mapper.SkillDtoMapper;
import dev.tsumakov.application.core.skill.port.in.GetSkillByIdUseCase;
import dev.tsumakov.domain.core.skill.repository.SkillRepository;

public class GetSkillByIdUseCaseImpl implements GetSkillByIdUseCase {

  private final SkillRepository repository;
  private final SkillDtoMapper mapper;

  public GetSkillByIdUseCaseImpl(SkillRepository repository, SkillDtoMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public SkillDto execute(Integer skillId) {
    var found = repository.findById(skillId)
        .orElseThrow(() -> new SkillNotFoundException("Skill with id " + skillId + " not found"));
    return mapper.toDto(found);
  }
}
