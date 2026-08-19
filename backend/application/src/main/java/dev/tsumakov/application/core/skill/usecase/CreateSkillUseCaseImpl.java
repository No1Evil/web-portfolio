package dev.tsumakov.application.core.skill.usecase;

import dev.tsumakov.application.core.skill.dto.in.CreateSkillDto;
import dev.tsumakov.application.core.skill.dto.outer.SkillDto;
import dev.tsumakov.application.core.skill.mapper.SkillDtoMapper;
import dev.tsumakov.application.core.skill.port.in.CreateSkillUseCase;
import dev.tsumakov.application.core.skillcategory.exception.SkillCategoryNotFoundException;
import dev.tsumakov.domain.core.skill.factory.SkillFactory;
import dev.tsumakov.domain.core.skill.model.Skill;
import dev.tsumakov.domain.core.skill.repository.SkillRepository;
import dev.tsumakov.domain.core.skillcategory.model.SkillCategory;
import dev.tsumakov.domain.core.skillcategory.repository.SkillCategoryRepository;

public class CreateSkillUseCaseImpl implements CreateSkillUseCase {

  private final SkillFactory skillFactory;
  private final SkillRepository skillRepository;
  private final SkillCategoryRepository skillCategoryRepository;
  private final SkillDtoMapper skillDtoMapper;

  public CreateSkillUseCaseImpl(SkillFactory skillFactory, SkillRepository skillRepository,
      SkillCategoryRepository skillCategoryRepository,
      SkillDtoMapper skillDtoMapper) {
    this.skillFactory = skillFactory;
    this.skillRepository = skillRepository;
    this.skillCategoryRepository = skillCategoryRepository;
    this.skillDtoMapper = skillDtoMapper;
  }

  @Override
  public SkillDto execute(CreateSkillDto command) {
    var category = findById(command.categoryId());
    var skill = createSkill(category.id(), command.name(), command.iconUrl());
    return skillDtoMapper.toDto(skillRepository.create(skill));
  }

  private SkillCategory findById(Integer categoryId) {
    return skillCategoryRepository.findById(categoryId)
        .orElseThrow(() -> new SkillCategoryNotFoundException(
            "Skill category with id " + categoryId + " not found"));
  }

  private Skill createSkill(Integer categoryId, String skillName, String iconUrl) {
    return skillFactory.createNew(categoryId, skillName, iconUrl);
  }
}
