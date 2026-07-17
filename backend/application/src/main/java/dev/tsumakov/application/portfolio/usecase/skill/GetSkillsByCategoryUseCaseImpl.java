package dev.tsumakov.application.portfolio.usecase.skill;

import dev.tsumakov.application.portfolio.dto.api.SkillDto;
import dev.tsumakov.application.portfolio.mapper.SkillDtoMapper;
import dev.tsumakov.application.portfolio.port.in.skill.GetSkillsByCategoryUseCase;
import dev.tsumakov.domain.portfolio.repository.SkillRepository;
import java.util.List;

public class GetSkillsByCategoryUseCaseImpl implements GetSkillsByCategoryUseCase {

  private final SkillRepository skillRepository;
  private final SkillDtoMapper skillDtoMapper;

  public GetSkillsByCategoryUseCaseImpl(SkillRepository skillRepository, SkillDtoMapper skillDtoMapper) {
    this.skillRepository = skillRepository;
    this.skillDtoMapper = skillDtoMapper;
  }

  @Override
  public List<SkillDto> execute(Integer categoryId) {
    return skillDtoMapper.toDtoList(skillRepository.findAllByCategory(categoryId));
  }
}
