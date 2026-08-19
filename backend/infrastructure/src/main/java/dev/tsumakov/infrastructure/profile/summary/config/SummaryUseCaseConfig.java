package dev.tsumakov.infrastructure.profile.summary.config;

import dev.tsumakov.application.profile.summary.mapper.UserSummaryDtoMapper;
import dev.tsumakov.application.profile.summary.port.in.GetUserSummaryUseCase;
import dev.tsumakov.application.profile.summary.port.in.UpdateUserSummaryUseCase;
import dev.tsumakov.application.profile.summary.usecase.GetUserSummaryUseCaseImpl;
import dev.tsumakov.application.profile.summary.usecase.UpdateUserSummaryUseCaseImpl;
import dev.tsumakov.domain.profile.summary.repository.UserSummaryRepository;
import dev.tsumakov.infrastructure.profile.summary.usecase.TransactionalUpdateUserSummaryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class SummaryUseCaseConfig {

  @Bean
  public UserSummaryDtoMapper userSummaryDtoMapper() {
    return UserSummaryDtoMapper.INSTANCE;
  }

  @Bean
  public UpdateUserSummaryUseCase updateUserSummaryUseCase(UserSummaryRepository repository,
      UserSummaryDtoMapper mapper, TransactionTemplate transactionTemplate) {
    return new TransactionalUpdateUserSummaryUseCase(new UpdateUserSummaryUseCaseImpl(repository, mapper),
        transactionTemplate);
  }

  @Bean
  public GetUserSummaryUseCase getUserSummaryUseCase(UserSummaryRepository repository, UserSummaryDtoMapper mapper) {
    return new GetUserSummaryUseCaseImpl(repository, mapper);
  }

}