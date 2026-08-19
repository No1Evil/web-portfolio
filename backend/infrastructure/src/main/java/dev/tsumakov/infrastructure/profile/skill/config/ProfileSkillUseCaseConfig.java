package dev.tsumakov.infrastructure.profile.skill.config;

import dev.tsumakov.application.profile.skill.mapper.UserSkillDtoMapper;
import dev.tsumakov.application.profile.skill.port.in.AssignSkillToUserUseCase;
import dev.tsumakov.application.profile.skill.port.in.GetAllUserSkillsUseCase;
import dev.tsumakov.application.profile.skill.port.in.UnassignSkillFromUserUseCase;
import dev.tsumakov.application.profile.skill.usecase.AssignSkillToUserUseCaseImpl;
import dev.tsumakov.application.profile.skill.usecase.GetAllUserSkillsUseCaseImpl;
import dev.tsumakov.application.profile.skill.usecase.UnassignSkillFromUserUseCaseImpl;
import dev.tsumakov.domain.core.skill.repository.UserSkillRepository;
import dev.tsumakov.infrastructure.profile.skill.usecase.TransactionalAssignSkillToUserUseCase;
import dev.tsumakov.infrastructure.profile.skill.usecase.TransactionalUnassignSkillFromUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class ProfileSkillUseCaseConfig {

  @Bean
  public UserSkillDtoMapper userSkillDtoMapper() {
    return UserSkillDtoMapper.INSTANCE;
  }

  @Bean
  public AssignSkillToUserUseCase assignSkillToUserUseCase(UserSkillRepository repository,
      TransactionTemplate transactionTemplate) {
    return new TransactionalAssignSkillToUserUseCase(new AssignSkillToUserUseCaseImpl(repository),
        transactionTemplate);
  }

  @Bean
  public UnassignSkillFromUserUseCase unassignSkillFromUserUseCase(UserSkillRepository repository,
      TransactionTemplate transactionTemplate) {
    return new TransactionalUnassignSkillFromUserUseCase(new UnassignSkillFromUserUseCaseImpl(repository),
        transactionTemplate);
  }

  @Bean
  public GetAllUserSkillsUseCase getAllUserSkillsUseCase(UserSkillRepository repository,
      UserSkillDtoMapper mapper) {
    return new GetAllUserSkillsUseCaseImpl(repository, mapper);
  }

}