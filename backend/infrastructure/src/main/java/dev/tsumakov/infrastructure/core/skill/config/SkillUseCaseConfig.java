package dev.tsumakov.infrastructure.core.skill.config;

import dev.tsumakov.application.core.skill.mapper.SkillDtoMapper;
import dev.tsumakov.application.core.skill.port.in.CreateSkillUseCase;
import dev.tsumakov.application.core.skill.port.in.DeleteSkillUseCase;
import dev.tsumakov.application.core.skill.port.in.GetAllSkillsUseCase;
import dev.tsumakov.application.core.skill.port.in.GetSkillByIdUseCase;
import dev.tsumakov.application.core.skill.port.in.UpdateSkillUseCase;
import dev.tsumakov.application.core.skill.usecase.CreateSkillUseCaseImpl;
import dev.tsumakov.application.core.skill.usecase.DeleteSkillUseCaseImpl;
import dev.tsumakov.application.core.skill.usecase.GetAllSkillsUseCaseImpl;
import dev.tsumakov.application.core.skill.usecase.GetSkillByIdUseCaseImpl;
import dev.tsumakov.application.core.skill.usecase.UpdateSkillUseCaseImpl;
import dev.tsumakov.domain.core.skill.factory.SkillFactory;
import dev.tsumakov.domain.core.skill.repository.SkillRepository;
import dev.tsumakov.infrastructure.core.skill.usecase.TransactionalCreateSkillUseCase;
import dev.tsumakov.infrastructure.core.skill.usecase.TransactionalDeleteSkillUseCase;
import dev.tsumakov.infrastructure.core.skill.usecase.TransactionalUpdateSkillUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class SkillUseCaseConfig {

  @Bean
  public SkillFactory skillFactory() {
    return new SkillFactory();
  }

  @Bean
  public SkillDtoMapper skillDtoMapper() {
    return SkillDtoMapper.INSTANCE;
  }

  @Bean
  public CreateSkillUseCase createSkillUseCase(SkillFactory skillFactory, SkillRepository skillRepository,
      SkillDtoMapper mapper, TransactionTemplate transactionTemplate) {
    return new TransactionalCreateSkillUseCase(
        new CreateSkillUseCaseImpl(skillFactory, skillRepository, mapper), transactionTemplate);
  }

  @Bean
  public UpdateSkillUseCase updateSkillUseCase(SkillRepository repository, SkillDtoMapper mapper,
      TransactionTemplate transactionTemplate) {
    return new TransactionalUpdateSkillUseCase(new UpdateSkillUseCaseImpl(repository, mapper), transactionTemplate);
  }

  @Bean
  public DeleteSkillUseCase deleteSkillUseCase(SkillRepository repository, TransactionTemplate transactionTemplate) {
    return new TransactionalDeleteSkillUseCase(new DeleteSkillUseCaseImpl(repository), transactionTemplate);
  }

  @Bean
  public GetAllSkillsUseCase getAllSkillsUseCase(SkillRepository repository, SkillDtoMapper mapper) {
    return new GetAllSkillsUseCaseImpl(repository, mapper);
  }

  @Bean
  public GetSkillByIdUseCase getSkillByIdUseCase(SkillRepository repository, SkillDtoMapper mapper) {
    return new GetSkillByIdUseCaseImpl(repository, mapper);
  }

}