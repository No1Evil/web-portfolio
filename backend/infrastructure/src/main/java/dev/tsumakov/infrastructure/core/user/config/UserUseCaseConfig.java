package dev.tsumakov.infrastructure.core.user.config;

import dev.tsumakov.application.core.user.mapper.UserDtoMapper;
import dev.tsumakov.application.core.user.port.in.AuthenticateUserUseCase;
import dev.tsumakov.application.core.user.port.in.UpdateUserPasswordUseCase;
import dev.tsumakov.application.core.user.usecase.AuthenticateUserUseCaseImpl;
import dev.tsumakov.application.core.user.usecase.UpdateUserPasswordUseCaseImpl;
import dev.tsumakov.domain.core.user.repository.UserRepository;
import dev.tsumakov.domain.shared.util.PasswordEncoder;
import dev.tsumakov.infrastructure.core.user.usecase.TransactionalUpdateUserPasswordUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class UserUseCaseConfig {

  @Bean
  public UserDtoMapper userDtoMapper() {
    return UserDtoMapper.INSTANCE;
  }

  @Bean
  public UpdateUserPasswordUseCase updateUserPasswordUseCase(PasswordEncoder passwordEncoder,
      UserRepository repository, UserDtoMapper mapper, TransactionTemplate transactionTemplate) {
    return new TransactionalUpdateUserPasswordUseCase(
        new UpdateUserPasswordUseCaseImpl(passwordEncoder, repository, mapper),
        transactionTemplate);
  }

  @Bean
  public AuthenticateUserUseCase authenticateUserUseCase(UserRepository repository,
      PasswordEncoder passwordEncoder, UserDtoMapper mapper) {
    return new AuthenticateUserUseCaseImpl(repository, passwordEncoder, mapper);
  }

}