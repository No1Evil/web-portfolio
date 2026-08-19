package dev.tsumakov.application.core.skill.usecase;

import dev.tsumakov.application.core.skill.dto.outer.SkillDto;
import dev.tsumakov.application.core.skill.mapper.SkillDtoMapper;
import dev.tsumakov.application.core.skill.port.in.GetAllSkillsUseCase;
import dev.tsumakov.domain.core.skill.repository.SkillRepository;
import java.util.List;

public class GetAllSkillsUseCaseImpl implements GetAllSkillsUseCase {

  private final SkillRepository repository;
  private final SkillDtoMapper mapper;

  public GetAllSkillsUseCaseImpl(SkillRepository repository, SkillDtoMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public List<SkillDto> execute() {
    return repository.findAll().stream().map(mapper::toDto).toList();
  }
}
