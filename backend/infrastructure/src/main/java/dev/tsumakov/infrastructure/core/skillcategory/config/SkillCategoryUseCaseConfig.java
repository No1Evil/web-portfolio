package dev.tsumakov.infrastructure.core.skillcategory.config;

import dev.tsumakov.application.core.skillcategory.mapper.SkillCategoryDtoMapper;
import dev.tsumakov.application.core.skillcategory.port.in.CreateSkillCategoryUseCase;
import dev.tsumakov.application.core.skillcategory.port.in.DeleteSkillCategoryUseCase;
import dev.tsumakov.application.core.skillcategory.port.in.GetAllSkillCategoriesUseCase;
import dev.tsumakov.application.core.skillcategory.port.in.GetSkillCategoryByIdUseCase;
import dev.tsumakov.application.core.skillcategory.port.in.UpdateSkillCategoryUseCase;
import dev.tsumakov.application.core.skillcategory.usecase.CreateSkillCategoryUseCaseImpl;
import dev.tsumakov.application.core.skillcategory.usecase.DeleteSkillCategoryUseCaseImpl;
import dev.tsumakov.application.core.skillcategory.usecase.GetAllSkillCategoriesUseCaseImpl;
import dev.tsumakov.application.core.skillcategory.usecase.GetSkillCategoryByIdUseCaseImpl;
import dev.tsumakov.application.core.skillcategory.usecase.UpdateSkillCategoryUseCaseImpl;
import dev.tsumakov.domain.core.skillcategory.factory.SkillCategoryFactory;
import dev.tsumakov.domain.core.skillcategory.repository.SkillCategoryRepository;
import dev.tsumakov.infrastructure.core.skillcategory.usecase.TransactionalCreateSkillCategoryUseCase;
import dev.tsumakov.infrastructure.core.skillcategory.usecase.TransactionalDeleteSkillCategoryUseCase;
import dev.tsumakov.infrastructure.core.skillcategory.usecase.TransactionalUpdateSkillCategoryUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class SkillCategoryUseCaseConfig {

  @Bean
  public SkillCategoryFactory skillCategoryFactory() {
    return new SkillCategoryFactory();
  }

  @Bean
  public SkillCategoryDtoMapper skillCategoryDtoMapper() {
    return SkillCategoryDtoMapper.INSTANCE;
  }

  @Bean
  public CreateSkillCategoryUseCase createSkillCategoryUseCase(SkillCategoryFactory factory,
      SkillCategoryRepository repository, SkillCategoryDtoMapper mapper, TransactionTemplate transactionTemplate) {
    return new TransactionalCreateSkillCategoryUseCase(
        new CreateSkillCategoryUseCaseImpl(factory, repository, mapper), transactionTemplate);
  }

  @Bean
  public UpdateSkillCategoryUseCase updateSkillCategoryUseCase(SkillCategoryRepository repository,
      SkillCategoryDtoMapper mapper, TransactionTemplate transactionTemplate) {
    return new TransactionalUpdateSkillCategoryUseCase(
        new UpdateSkillCategoryUseCaseImpl(repository, mapper), transactionTemplate);
  }

  @Bean
  public DeleteSkillCategoryUseCase deleteSkillCategoryUseCase(SkillCategoryRepository repository,
      TransactionTemplate transactionTemplate) {
    return new TransactionalDeleteSkillCategoryUseCase(new DeleteSkillCategoryUseCaseImpl(repository),
        transactionTemplate);
  }

  @Bean
  public GetAllSkillCategoriesUseCase getAllSkillCategoriesUseCase(SkillCategoryRepository repository,
      SkillCategoryDtoMapper mapper) {
    return new GetAllSkillCategoriesUseCaseImpl(repository, mapper);
  }

  @Bean
  public GetSkillCategoryByIdUseCase getSkillCategoryByIdUseCase(SkillCategoryRepository repository,
      SkillCategoryDtoMapper mapper) {
    return new GetSkillCategoryByIdUseCaseImpl(repository, mapper);
  }

}