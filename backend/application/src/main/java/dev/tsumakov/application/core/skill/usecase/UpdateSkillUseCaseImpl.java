package dev.tsumakov.application.core.skill.usecase;

import dev.tsumakov.application.core.skill.dto.in.UpdateSkillDto;
import dev.tsumakov.application.core.skill.dto.outer.SkillDto;
import dev.tsumakov.application.core.skill.exception.SkillNotFoundException;
import dev.tsumakov.application.core.skill.mapper.SkillDtoMapper;
import dev.tsumakov.application.core.skill.port.in.UpdateSkillUseCase;
import dev.tsumakov.domain.core.skill.model.Skill;
import dev.tsumakov.domain.core.skill.repository.SkillRepository;

public class UpdateSkillUseCaseImpl implements UpdateSkillUseCase {

  private final SkillRepository skillRepository;
  private final SkillDtoMapper mapper;

  public UpdateSkillUseCaseImpl(SkillRepository skillRepository, SkillDtoMapper mapper) {
    this.skillRepository = skillRepository;
    this.mapper = mapper;
  }

  @Override
  public SkillDto execute(UpdateSkillDto command) {
    Skill currentSkill = findSkill(command.skillId());

    Skill updatedSkill = currentSkill;
    updatedSkill = updateCategoryId(updatedSkill, command.categoryId());
    updatedSkill = updateName(updatedSkill, command.name());
    updatedSkill = updateIconUrl(updatedSkill, command.iconUrl());

    if (!currentSkill.equals(updatedSkill)) {
      Skill savedSkill = skillRepository.update(updatedSkill.withIncrementedVersion());
      return mapper.toDto(savedSkill);
    }

    return mapper.toDto(currentSkill);
  }

  private Skill findSkill(Integer skillId) {
    return skillRepository.findById(skillId)
        .orElseThrow(() -> new SkillNotFoundException("Skill with id " + skillId + " not found"));
  }

  private Skill updateCategoryId(Skill skill, Integer categoryId) {
    return categoryId == null ? skill : skill.updateCategoryId(categoryId);
  }

  private Skill updateName(Skill skill, String name) {
    return name == null ? skill : skill.updateName(name);
  }

  private Skill updateIconUrl(Skill skill, String iconUrl) {
    return iconUrl == null ? skill : skill.updateIconUrl(iconUrl);
  }
}
