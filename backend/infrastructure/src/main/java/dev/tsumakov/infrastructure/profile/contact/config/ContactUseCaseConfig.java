package dev.tsumakov.infrastructure.profile.contact.config;

import dev.tsumakov.application.profile.contact.mapper.UserContactDtoMapper;
import dev.tsumakov.application.profile.contact.port.in.CreateUserContactUseCase;
import dev.tsumakov.application.profile.contact.port.in.DeleteUserContactUseCase;
import dev.tsumakov.application.profile.contact.port.in.GetAllUserContactsUseCase;
import dev.tsumakov.application.profile.contact.port.in.GetUserContactByIdUseCase;
import dev.tsumakov.application.profile.contact.port.in.UpdateUserContactUseCase;
import dev.tsumakov.application.profile.contact.usecase.CreateUserContactUseCaseImpl;
import dev.tsumakov.application.profile.contact.usecase.DeleteUserContactUseCaseImpl;
import dev.tsumakov.application.profile.contact.usecase.GetAllUserContactsUseCaseImpl;
import dev.tsumakov.application.profile.contact.usecase.GetUserContactByIdUseCaseImpl;
import dev.tsumakov.application.profile.contact.usecase.UpdateUserContactUseCaseImpl;
import dev.tsumakov.domain.profile.contact.factory.UserContactFactory;
import dev.tsumakov.domain.profile.contact.repository.UserContactRepository;
import dev.tsumakov.infrastructure.profile.contact.usecase.TransactionalCreateUserContactUseCase;
import dev.tsumakov.infrastructure.profile.contact.usecase.TransactionalDeleteUserContactUseCase;
import dev.tsumakov.infrastructure.profile.contact.usecase.TransactionalUpdateUserContactUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class ContactUseCaseConfig {

  @Bean
  public UserContactFactory userContactFactory() {
    return new UserContactFactory();
  }

  @Bean
  public UserContactDtoMapper userContactDtoMapper() {
    return UserContactDtoMapper.INSTANCE;
  }

  @Bean
  public CreateUserContactUseCase createUserContactUseCase(UserContactFactory factory,
      UserContactRepository repository, UserContactDtoMapper mapper, TransactionTemplate transactionTemplate) {
    return new TransactionalCreateUserContactUseCase(
        new CreateUserContactUseCaseImpl(factory, repository, mapper), transactionTemplate);
  }

  @Bean
  public UpdateUserContactUseCase updateUserContactUseCase(UserContactRepository repository,
      UserContactDtoMapper mapper, TransactionTemplate transactionTemplate) {
    return new TransactionalUpdateUserContactUseCase(
        new UpdateUserContactUseCaseImpl(repository, mapper), transactionTemplate);
  }

  @Bean
  public DeleteUserContactUseCase deleteUserContactUseCase(UserContactRepository repository,
      TransactionTemplate transactionTemplate) {
    return new TransactionalDeleteUserContactUseCase(new DeleteUserContactUseCaseImpl(repository),
        transactionTemplate);
  }

  @Bean
  public GetAllUserContactsUseCase getAllUserContactsUseCase(UserContactRepository repository,
      UserContactDtoMapper mapper) {
    return new GetAllUserContactsUseCaseImpl(repository, mapper);
  }

  @Bean
  public GetUserContactByIdUseCase getUserContactByIdUseCase(UserContactRepository repository,
      UserContactDtoMapper mapper) {
    return new GetUserContactByIdUseCaseImpl(repository, mapper);
  }

}