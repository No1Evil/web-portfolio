package dev.tsumakov.infrastructure.profile.experience.config;

import dev.tsumakov.application.profile.experience.mapper.UserExperiencePathDtoMapper;
import dev.tsumakov.application.profile.experience.port.in.CreateUserExperiencePathUseCase;
import dev.tsumakov.application.profile.experience.port.in.DeleteUserExperiencePathUseCase;
import dev.tsumakov.application.profile.experience.port.in.GetAllUserExperiencePathsUseCase;
import dev.tsumakov.application.profile.experience.port.in.GetUserExperiencePathByIdUseCase;
import dev.tsumakov.application.profile.experience.port.in.UpdateUserExperiencePathUseCase;
import dev.tsumakov.application.profile.experience.usecase.CreateUserExperiencePathUseCaseImpl;
import dev.tsumakov.application.profile.experience.usecase.DeleteUserExperiencePathUseCaseImpl;
import dev.tsumakov.application.profile.experience.usecase.GetAllUserExperiencePathsUseCaseImpl;
import dev.tsumakov.application.profile.experience.usecase.GetUserExperiencePathByIdUseCaseImpl;
import dev.tsumakov.application.profile.experience.usecase.UpdateUserExperiencePathUseCaseImpl;
import dev.tsumakov.domain.profile.experience.factory.UserExperiencePathFactory;
import dev.tsumakov.domain.profile.experience.repository.UserExperiencePathRepository;
import dev.tsumakov.domain.shared.util.UuidGenerator;
import dev.tsumakov.infrastructure.profile.experience.usecase.TransactionalCreateUserExperiencePathUseCase;
import dev.tsumakov.infrastructure.profile.experience.usecase.TransactionalDeleteUserExperiencePathUseCase;
import dev.tsumakov.infrastructure.profile.experience.usecase.TransactionalUpdateUserExperiencePathUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class ExperienceUseCaseConfig {

  @Bean
  public UserExperiencePathFactory userExperiencePathFactory(UuidGenerator uuidGenerator) {
    return new UserExperiencePathFactory(uuidGenerator);
  }

  @Bean
  public UserExperiencePathDtoMapper userExperiencePathDtoMapper() {
    return UserExperiencePathDtoMapper.INSTANCE;
  }

  @Bean
  public CreateUserExperiencePathUseCase createUserExperiencePathUseCase(UserExperiencePathFactory factory,
      UserExperiencePathRepository repository, UserExperiencePathDtoMapper mapper,
      TransactionTemplate transactionTemplate) {
    return new TransactionalCreateUserExperiencePathUseCase(
        new CreateUserExperiencePathUseCaseImpl(factory, repository, mapper), transactionTemplate);
  }

  @Bean
  public UpdateUserExperiencePathUseCase updateUserExperiencePathUseCase(UserExperiencePathRepository repository,
      UserExperiencePathDtoMapper mapper, TransactionTemplate transactionTemplate) {
    return new TransactionalUpdateUserExperiencePathUseCase(
        new UpdateUserExperiencePathUseCaseImpl(repository, mapper), transactionTemplate);
  }

  @Bean
  public DeleteUserExperiencePathUseCase deleteUserExperiencePathUseCase(UserExperiencePathRepository repository,
      TransactionTemplate transactionTemplate) {
    return new TransactionalDeleteUserExperiencePathUseCase(new DeleteUserExperiencePathUseCaseImpl(repository),
        transactionTemplate);
  }

  @Bean
  public GetAllUserExperiencePathsUseCase getAllUserExperiencePathsUseCase(UserExperiencePathRepository repository,
      UserExperiencePathDtoMapper mapper) {
    return new GetAllUserExperiencePathsUseCaseImpl(repository, mapper);
  }

  @Bean
  public GetUserExperiencePathByIdUseCase getUserExperiencePathByIdUseCase(UserExperiencePathRepository repository,
      UserExperiencePathDtoMapper mapper) {
    return new GetUserExperiencePathByIdUseCaseImpl(repository, mapper);
  }

}