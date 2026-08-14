package dev.tsumakov.infrastructure.portfolio.config;

import dev.tsumakov.application.portfolio.mapper.SkillDtoMapper;
import dev.tsumakov.application.portfolio.port.in.skill.CreateSkillUseCase;
import dev.tsumakov.application.portfolio.port.in.skill.DeleteSkillUseCase;
import dev.tsumakov.application.portfolio.port.in.skill.GetAllSkillsUseCase;
import dev.tsumakov.application.portfolio.port.in.skill.GetSkillByIdUseCase;
import dev.tsumakov.application.portfolio.port.in.skill.GetSkillsByCategoryUseCase;
import dev.tsumakov.application.portfolio.usecase.skill.CreateSkillUseCaseImpl;
import dev.tsumakov.application.portfolio.usecase.skill.DeleteSkillUseCaseImpl;
import dev.tsumakov.application.portfolio.usecase.skill.GetAllSkillsUseCaseImpl;
import dev.tsumakov.application.portfolio.usecase.skill.GetSkillByIdUseCaseImpl;
import dev.tsumakov.application.portfolio.usecase.skill.GetSkillsByCategoryUseCaseImpl;
import dev.tsumakov.domain.portfolio.repository.SkillRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

  @Bean
  public CreateSkillUseCase createSkillUseCase(SkillRepository skillRepository, SkillDtoMapper mapper) {
    return new CreateSkillUseCaseImpl(skillRepository, mapper);
  }

  @Bean
  public DeleteSkillUseCase deleteSkillUseCase(SkillRepository skillRepository) {
    return new DeleteSkillUseCaseImpl(skillRepository);
  }

  @Bean
  public GetAllSkillsUseCase getAllSkillsUseCase(SkillRepository skillRepository, SkillDtoMapper mapper) {
    return new GetAllSkillsUseCaseImpl(skillRepository, mapper);
  }

  @Bean
  public GetSkillByIdUseCase getSkillByIdUseCase(SkillRepository skillRepository, SkillDtoMapper mapper) {
    return new GetSkillByIdUseCaseImpl(skillRepository, mapper);
  }

  @Bean
  public GetSkillsByCategoryUseCase getSkillsByCategoryUseCase(SkillRepository skillRepository, SkillDtoMapper mapper) {
    return new GetSkillsByCategoryUseCaseImpl(skillRepository, mapper);
  }

}
