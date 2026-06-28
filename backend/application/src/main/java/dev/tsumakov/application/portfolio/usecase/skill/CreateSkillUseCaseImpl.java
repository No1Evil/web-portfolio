package dev.tsumakov.application.portfolio.usecase.skill;

import dev.tsumakov.application.portfolio.dto.api.SkillDto;
import dev.tsumakov.application.portfolio.dto.in.CreateSkillDto;
import dev.tsumakov.application.portfolio.mapper.SkillDtoMapper;
import dev.tsumakov.application.portfolio.port.in.skill.CreateSkillUseCase;
import dev.tsumakov.domain.portfolio.model.Skill;
import dev.tsumakov.domain.portfolio.repository.SkillRepository;

public class CreateSkillUseCaseImpl implements CreateSkillUseCase {

  private final SkillRepository skillRepository;
  private final SkillDtoMapper skillDtoMapper;

  public CreateSkillUseCaseImpl(SkillRepository skillRepository, SkillDtoMapper skillDtoMapper) {
    this.skillRepository = skillRepository;
    this.skillDtoMapper = skillDtoMapper;
  }

  @Override
  public SkillDto execute(CreateSkillDto command) {
    var skill = new Skill(null, command.categoryId(), command.name());
    skillRepository.save(skill);
    return skillDtoMapper.toDto(skill);
  }
}
