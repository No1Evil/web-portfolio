package dev.tsumakov.infrastructure.core.config;

import dev.tsumakov.application.core.mapper.RoleDtoMapper;
import dev.tsumakov.application.core.mapper.UserDtoMapper;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;

@Mapper
public class MappersConfig {

  @Bean
  public RoleDtoMapper roleDtoMapper() {
    return RoleDtoMapper.INSTANCE;
  }

  @Bean
  public UserDtoMapper userDtoMapper() {
    return UserDtoMapper.INSTANCE;
  }

}
