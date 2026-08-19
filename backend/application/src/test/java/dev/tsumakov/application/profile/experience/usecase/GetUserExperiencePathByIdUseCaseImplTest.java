package dev.tsumakov.application.profile.experience.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.profile.experience.dto.outer.UserExperiencePathDto;
import dev.tsumakov.application.profile.experience.exception.UserExperiencePathNotFoundException;
import dev.tsumakov.application.profile.experience.mapper.UserExperiencePathDtoMapper;
import dev.tsumakov.domain.profile.experience.model.UserExperiencePath;
import dev.tsumakov.domain.profile.experience.repository.UserExperiencePathRepository;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetUserExperiencePathByIdUseCaseImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.now();
  private static final UUID ID = UUID.randomUUID();

  @Mock
  private UserExperiencePathRepository repository;
  @Mock
  private UserExperiencePathDtoMapper mapper;

  private GetUserExperiencePathByIdUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new GetUserExperiencePathByIdUseCaseImpl(repository, mapper);
  }

  @Test
  void shouldReturnPathDtoWhenFound() {
    var path = new UserExperiencePath(ID, "Google", "Software Engineer", "Mountain View", Map.of("en", "Backend"), NOW, NOW.plusYears(2), false, NOW, NOW, 1L);
    var expectedDto = new UserExperiencePathDto(ID, "Google", "Software Engineer", "Mountain View", Map.of("en", "Backend"), NOW, NOW.plusYears(2), false, NOW, NOW);

    when(repository.findById(ID)).thenReturn(Optional.of(path));
    when(mapper.toDto(path)).thenReturn(expectedDto);

    var result = useCase.execute(ID);

    assertThat(result).isEqualTo(expectedDto);
  }

  @Test
  void shouldThrowNotFoundExceptionWhenPathMissing() {
    when(repository.findById(ID)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> useCase.execute(ID))
        .isInstanceOf(UserExperiencePathNotFoundException.class)
        .hasMessage("User experience path with id " + ID + " not found");

    verify(mapper, never()).toDto(org.mockito.ArgumentMatchers.any());
  }
}