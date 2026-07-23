package dev.tsumakov.infrastructure.portfolio.config;

import dev.tsumakov.application.portfolio.mapper.SkillCategoryDtoMapper;
import dev.tsumakov.application.portfolio.mapper.SkillDtoMapper;
import dev.tsumakov.application.portfolio.mapper.UserProjectDtoMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappersConfig {

  @Bean
  public SkillCategoryDtoMapper skillCategoryDtoMapper() {
    return SkillCategoryDtoMapper.INSTANCE;
  }

  @Bean
  public SkillDtoMapper skillDtoMapper() {
    return SkillDtoMapper.INSTANCE;
  }

  @Bean
  public UserProjectDtoMapper userProjectDtoMapper() {
    return UserProjectDtoMapper.INSTANCE;
  }
}
