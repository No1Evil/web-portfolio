package dev.tsumakov.infrastructure.profile.education.config;

import dev.tsumakov.application.profile.education.mapper.UserEducationPathDtoMapper;
import dev.tsumakov.application.profile.education.port.in.CreateUserEducationPathUseCase;
import dev.tsumakov.application.profile.education.port.in.DeleteUserEducationPathUseCase;
import dev.tsumakov.application.profile.education.port.in.GetAllUserEducationPathsUseCase;
import dev.tsumakov.application.profile.education.port.in.GetUserEducationPathByIdUseCase;
import dev.tsumakov.application.profile.education.port.in.UpdateUserEducationPathUseCase;
import dev.tsumakov.application.profile.education.usecase.CreateUserEducationPathUseCaseImpl;
import dev.tsumakov.application.profile.education.usecase.DeleteUserEducationPathUseCaseImpl;
import dev.tsumakov.application.profile.education.usecase.GetAllUserEducationPathsUseCaseImpl;
import dev.tsumakov.application.profile.education.usecase.GetUserEducationPathByIdUseCaseImpl;
import dev.tsumakov.application.profile.education.usecase.UpdateUserEducationPathUseCaseImpl;
import dev.tsumakov.domain.profile.education.factory.UserEducationPathFactory;
import dev.tsumakov.domain.profile.education.repository.UserEducationPathRepository;
import dev.tsumakov.domain.shared.util.UuidGenerator;
import dev.tsumakov.infrastructure.profile.education.usecase.TransactionalCreateUserEducationPathUseCase;
import dev.tsumakov.infrastructure.profile.education.usecase.TransactionalDeleteUserEducationPathUseCase;
import dev.tsumakov.infrastructure.profile.education.usecase.TransactionalUpdateUserEducationPathUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class EducationUseCaseConfig {

  @Bean
  public UserEducationPathFactory userEducationPathFactory(UuidGenerator uuidGenerator) {
    return new UserEducationPathFactory(uuidGenerator);
  }

  @Bean
  public UserEducationPathDtoMapper userEducationPathDtoMapper() {
    return UserEducationPathDtoMapper.INSTANCE;
  }

  @Bean
  public CreateUserEducationPathUseCase createUserEducationPathUseCase(UserEducationPathFactory factory,
      UserEducationPathRepository repository, UserEducationPathDtoMapper mapper,
      TransactionTemplate transactionTemplate) {
    return new TransactionalCreateUserEducationPathUseCase(
        new CreateUserEducationPathUseCaseImpl(factory, repository, mapper), transactionTemplate);
  }

  @Bean
  public UpdateUserEducationPathUseCase updateUserEducationPathUseCase(UserEducationPathRepository repository,
      UserEducationPathDtoMapper mapper, TransactionTemplate transactionTemplate) {
    return new TransactionalUpdateUserEducationPathUseCase(
        new UpdateUserEducationPathUseCaseImpl(repository, mapper), transactionTemplate);
  }

  @Bean
  public DeleteUserEducationPathUseCase deleteUserEducationPathUseCase(UserEducationPathRepository repository,
      TransactionTemplate transactionTemplate) {
    return new TransactionalDeleteUserEducationPathUseCase(new DeleteUserEducationPathUseCaseImpl(repository),
        transactionTemplate);
  }

  @Bean
  public GetAllUserEducationPathsUseCase getAllUserEducationPathsUseCase(UserEducationPathRepository repository,
      UserEducationPathDtoMapper mapper) {
    return new GetAllUserEducationPathsUseCaseImpl(repository, mapper);
  }

  @Bean
  public GetUserEducationPathByIdUseCase getUserEducationPathByIdUseCase(UserEducationPathRepository repository,
      UserEducationPathDtoMapper mapper) {
    return new GetUserEducationPathByIdUseCaseImpl(repository, mapper);
  }

}