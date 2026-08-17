package dev.tsumakov.application.core.skillcategory.usecase;

import dev.tsumakov.application.core.skillcategory.dto.in.CreateSkillCategoryDto;
import dev.tsumakov.application.core.skillcategory.dto.outer.SkillCategoryDto;
import dev.tsumakov.application.core.skillcategory.mapper.SkillCategoryDtoMapper;
import dev.tsumakov.application.core.skillcategory.port.in.CreateSkillCategoryUseCase;
import dev.tsumakov.domain.core.skillcategory.factory.SkillCategoryFactory;
import dev.tsumakov.domain.core.skillcategory.model.SkillCategory;
import dev.tsumakov.domain.core.skillcategory.repository.SkillCategoryRepository;

public class CreateSkillCategoryUseCaseImpl implements CreateSkillCategoryUseCase {

  private final SkillCategoryFactory factory;
  private final SkillCategoryRepository repository;
  private final SkillCategoryDtoMapper mapper;

  public CreateSkillCategoryUseCaseImpl(SkillCategoryFactory factory, SkillCategoryRepository repository,
      SkillCategoryDtoMapper mapper) {
    this.factory = factory;
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public SkillCategoryDto execute(CreateSkillCategoryDto command) {
    SkillCategory skillCategory = factory.createNew(command.name(), command.iconUrl());
    SkillCategory created = repository.create(skillCategory);
    return mapper.toDto(created);
  }
}
