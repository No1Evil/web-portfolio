package dev.tsumakov.application.profile.experience.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.profile.experience.dto.outer.UserExperiencePathDto;
import dev.tsumakov.application.profile.experience.mapper.UserExperiencePathDtoMapper;
import dev.tsumakov.domain.profile.experience.model.UserExperiencePath;
import dev.tsumakov.domain.profile.experience.repository.UserExperiencePathRepository;
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
class GetAllUserExperiencePathsUseCaseImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.now();
  private static final UUID ID_1 = UUID.randomUUID();
  private static final UUID ID_2 = UUID.randomUUID();

  @Mock
  private UserExperiencePathRepository repository;
  @Mock
  private UserExperiencePathDtoMapper mapper;

  private GetAllUserExperiencePathsUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new GetAllUserExperiencePathsUseCaseImpl(repository, mapper);
  }

  @Test
  void shouldReturnAllPathsMapped() {
    var google = new UserExperiencePath(ID_1, "Google", "Software Engineer", "Mountain View", Map.of("en", "Backend"), NOW, NOW.plusYears(2), false, NOW, NOW, 1L);
    var meta = new UserExperiencePath(ID_2, "Meta", "Senior Engineer", "London", Map.of("en", "Distributed Systems"), NOW, NOW.plusYears(1), true, NOW, NOW, 1L);
    var googleDto = new UserExperiencePathDto(ID_1, "Google", "Software Engineer", "Mountain View", Map.of("en", "Backend"), NOW, NOW.plusYears(2), false, NOW, NOW);
    var metaDto = new UserExperiencePathDto(ID_2, "Meta", "Senior Engineer", "London", Map.of("en", "Distributed Systems"), NOW, NOW.plusYears(1), true, NOW, NOW);

    when(repository.findAll()).thenReturn(List.of(google, meta));
    when(mapper.toDto(google)).thenReturn(googleDto);
    when(mapper.toDto(meta)).thenReturn(metaDto);

    var result = useCase.execute();

    assertThat(result).containsExactly(googleDto, metaDto);
    verify(mapper).toDto(google);
    verify(mapper).toDto(meta);
  }

  @Test
  void shouldReturnEmptyListWhenRepositoryEmpty() {
    when(repository.findAll()).thenReturn(List.of());

    var result = useCase.execute();

    assertThat(result).isEmpty();
    verify(mapper, never()).toDto(org.mockito.ArgumentMatchers.any());
  }
}