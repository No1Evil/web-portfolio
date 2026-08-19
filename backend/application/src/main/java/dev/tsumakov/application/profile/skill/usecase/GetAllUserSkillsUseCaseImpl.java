package dev.tsumakov.application.profile.skill.usecase;

import dev.tsumakov.application.profile.skill.dto.outer.UserSkillDto;
import dev.tsumakov.application.profile.skill.mapper.UserSkillDtoMapper;
import dev.tsumakov.application.profile.skill.port.in.GetAllUserSkillsUseCase;
import dev.tsumakov.domain.core.skill.repository.UserSkillRepository;
import java.util.List;

public class GetAllUserSkillsUseCaseImpl implements GetAllUserSkillsUseCase {

  private final UserSkillRepository repository;
  private final UserSkillDtoMapper mapper;

  public GetAllUserSkillsUseCaseImpl(UserSkillRepository repository, UserSkillDtoMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public List<UserSkillDto> execute() {
    return repository.findAllByUserId(1).stream().map(mapper::toDto).toList();
  }
}
