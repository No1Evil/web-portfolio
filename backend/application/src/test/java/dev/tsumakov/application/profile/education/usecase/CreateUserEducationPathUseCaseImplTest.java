package dev.tsumakov.application.profile.education.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.profile.education.dto.in.CreateUserEducationPathDto;
import dev.tsumakov.application.profile.education.dto.outer.UserEducationPathDto;
import dev.tsumakov.application.profile.education.mapper.UserEducationPathDtoMapper;
import dev.tsumakov.domain.profile.education.factory.UserEducationPathFactory;
import dev.tsumakov.domain.profile.education.model.UserEducationPath;
import dev.tsumakov.domain.profile.education.repository.UserEducationPathRepository;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateUserEducationPathUseCaseImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.now();
  private static final UUID ID = UUID.randomUUID();

  @Mock
  private UserEducationPathFactory factory;
  @Mock
  private UserEducationPathRepository repository;
  @Mock
  private UserEducationPathDtoMapper mapper;

  private CreateUserEducationPathUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new CreateUserEducationPathUseCaseImpl(factory, repository, mapper);
  }

  @Test
  void shouldCreateEducationPathAndReturnDto() {
    var command = new CreateUserEducationPathDto("MIT", "Boston", Map.of("en", "BSc"), NOW, NOW.plusYears(4), true);
    var freshPath = new UserEducationPath(ID, "MIT", "Boston", Map.of("en", "BSc"), NOW, NOW.plusYears(4), true, NOW, NOW, 1L);
    var savedPath = new UserEducationPath(ID, "MIT", "Boston", Map.of("en", "BSc"), NOW, NOW.plusYears(4), true, NOW, NOW, 1L);
    var expectedDto = new UserEducationPathDto(ID, "MIT", "Boston", Map.of("en", "BSc"), NOW, NOW.plusYears(4), true, NOW, NOW);

    when(factory.createNew(command.title(), command.location(), command.description(),
        command.startDate(), command.endDate(), command.present())).thenReturn(freshPath);
    when(repository.create(freshPath)).thenReturn(savedPath);
    when(mapper.toDto(savedPath)).thenReturn(expectedDto);

    var result = useCase.execute(command);

    assertThat(result).isEqualTo(expectedDto);
    verify(factory).createNew("MIT", "Boston", Map.of("en", "BSc"), NOW, NOW.plusYears(4), true);
    verify(repository).create(freshPath);
    verify(mapper).toDto(savedPath);
  }
}