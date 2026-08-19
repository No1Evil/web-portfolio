package dev.tsumakov.application.profile.education.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.profile.education.dto.outer.UserEducationPathDto;
import dev.tsumakov.application.profile.education.mapper.UserEducationPathDtoMapper;
import dev.tsumakov.domain.profile.education.model.UserEducationPath;
import dev.tsumakov.domain.profile.education.repository.UserEducationPathRepository;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetAllUserEducationPathsUseCaseImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.now();
  private static final UUID ID_1 = UUID.randomUUID();
  private static final UUID ID_2 = UUID.randomUUID();

  @Mock
  private UserEducationPathRepository repository;
  @Mock
  private UserEducationPathDtoMapper mapper;

  private GetAllUserEducationPathsUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new GetAllUserEducationPathsUseCaseImpl(repository, mapper);
  }

  @Test
  void shouldReturnAllPathsMapped() {
    var mit = new UserEducationPath(ID_1, "MIT", "Boston", Map.of("en", "BSc"), NOW, NOW.plusYears(4), true, NOW, NOW, 1L);
    var harvard = new UserEducationPath(ID_2, "Harvard", "Cambridge", Map.of("en", "MSc"), NOW, NOW.plusYears(2), false, NOW, NOW, 1L);
    var mitDto = new UserEducationPathDto(ID_1, "MIT", "Boston", Map.of("en", "BSc"), NOW, NOW.plusYears(4), true, NOW, NOW);
    var harvardDto = new UserEducationPathDto(ID_2, "Harvard", "Cambridge", Map.of("en", "MSc"), NOW, NOW.plusYears(2), false, NOW, NOW);

    when(repository.findAll()).thenReturn(List.of(mit, harvard));
    when(mapper.toDto(mit)).thenReturn(mitDto);
    when(mapper.toDto(harvard)).thenReturn(harvardDto);

    var result = useCase.execute();

    assertThat(result).containsExactly(mitDto, harvardDto);
    verify(mapper).toDto(mit);
    verify(mapper).toDto(harvard);
  }

  @Test
  void shouldReturnEmptyListWhenRepositoryEmpty() {
    when(repository.findAll()).thenReturn(List.of());

    var result = useCase.execute();

    assertThat(result).isEmpty();
    verify(mapper, never()).toDto(org.mockito.ArgumentMatchers.any());
  }
}