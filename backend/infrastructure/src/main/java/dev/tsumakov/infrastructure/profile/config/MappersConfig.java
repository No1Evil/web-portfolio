package dev.tsumakov.infrastructure.profile.config;

import dev.tsumakov.application.profile.mapper.EducationDtoMapper;
import dev.tsumakov.application.profile.mapper.ExperienceDtoMapper;
import dev.tsumakov.application.profile.mapper.UserContactDtoMapper;
import dev.tsumakov.application.profile.mapper.UserProfileDtoMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappersConfig {

  @Bean
  public EducationDtoMapper educationDtoMapper() {
    return EducationDtoMapper.INSTANCE;
  }

  @Bean
  public ExperienceDtoMapper experienceDtoMapper() {
    return ExperienceDtoMapper.INSTANCE;
  }

  @Bean
  public UserContactDtoMapper userContactDtoMapper() {
    return UserContactDtoMapper.INSTANCE;
  }

  @Bean
  public UserProfileDtoMapper userProfileDtoMapper() {
    return UserProfileDtoMapper.INSTANCE;
  }

}
