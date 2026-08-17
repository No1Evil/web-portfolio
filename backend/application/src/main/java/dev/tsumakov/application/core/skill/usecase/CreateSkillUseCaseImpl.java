package dev.tsumakov.application.core.skill.usecase;

import dev.tsumakov.application.core.skill.dto.in.CreateSkillDto;
import dev.tsumakov.application.core.skill.dto.outer.SkillDto;
import dev.tsumakov.application.core.skill.mapper.SkillDtoMapper;
import dev.tsumakov.application.core.skill.port.in.CreateSkillUseCase;
import dev.tsumakov.domain.core.skill.factory.SkillFactory;
import dev.tsumakov.domain.core.skill.model.Skill;
import dev.tsumakov.domain.core.skill.repository.SkillRepository;

public class CreateSkillUseCaseImpl implements CreateSkillUseCase {

  private final SkillFactory skillFactory;
  private final SkillRepository skillRepository;
  private final SkillDtoMapper skillDtoMapper;

  public CreateSkillUseCaseImpl(SkillFactory skillFactory, SkillRepository skillRepository,
      SkillDtoMapper skillDtoMapper) {
    this.skillFactory = skillFactory;
    this.skillRepository = skillRepository;
    this.skillDtoMapper = skillDtoMapper;
  }

  @Override
  public SkillDto execute(CreateSkillDto command) {
    Skill skill = skillFactory.createNew(command.categoryId(), command.name(), command.iconUrl());
    return skillDtoMapper.toDto(skillRepository.create(skill));
  }
}
