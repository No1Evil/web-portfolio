package dev.tsumakov.application.profile.experience.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.profile.experience.dto.in.CreateUserExperiencePathDto;
import dev.tsumakov.application.profile.experience.dto.outer.UserExperiencePathDto;
import dev.tsumakov.application.profile.experience.mapper.UserExperiencePathDtoMapper;
import dev.tsumakov.domain.profile.experience.factory.UserExperiencePathFactory;
import dev.tsumakov.domain.profile.experience.model.UserExperiencePath;
import dev.tsumakov.domain.profile.experience.repository.UserExperiencePathRepository;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateUserExperiencePathUseCaseImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.now();
  private static final UUID ID = UUID.randomUUID();

  @Mock
  private UserExperiencePathFactory factory;
  @Mock
  private UserExperiencePathRepository repository;
  @Mock
  private UserExperiencePathDtoMapper mapper;

  private CreateUserExperiencePathUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new CreateUserExperiencePathUseCaseImpl(factory, repository, mapper);
  }

  @Test
  void shouldCreateExperiencePathAndReturnDto() {
    var command = new CreateUserExperiencePathDto("Google", "Software Engineer", "Mountain View", Map.of("en", "Backend"), NOW, NOW.plusYears(2), false);
    var freshPath = new UserExperiencePath(ID, "Google", "Software Engineer", "Mountain View", Map.of("en", "Backend"), NOW, NOW.plusYears(2), false, NOW, NOW, 1L);
    var savedPath = new UserExperiencePath(ID, "Google", "Software Engineer", "Mountain View", Map.of("en", "Backend"), NOW, NOW.plusYears(2), false, NOW, NOW, 1L);
    var expectedDto = new UserExperiencePathDto(ID, "Google", "Software Engineer", "Mountain View", Map.of("en", "Backend"), NOW, NOW.plusYears(2), false, NOW, NOW);

    when(factory.createNew(command.title(), command.companyName(), command.location(),
        command.description(), command.startDate(), command.endDate(), command.present())).thenReturn(freshPath);
    when(repository.create(freshPath)).thenReturn(savedPath);
    when(mapper.toDto(savedPath)).thenReturn(expectedDto);

    var result = useCase.execute(command);

    assertThat(result).isEqualTo(expectedDto);
    verify(factory).createNew("Google", "Software Engineer", "Mountain View", Map.of("en", "Backend"), NOW, NOW.plusYears(2), false);
    verify(repository).create(freshPath);
    verify(mapper).toDto(savedPath);
  }
}