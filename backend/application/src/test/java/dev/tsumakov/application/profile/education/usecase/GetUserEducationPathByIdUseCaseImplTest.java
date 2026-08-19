package dev.tsumakov.application.profile.education.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.profile.education.dto.outer.UserEducationPathDto;
import dev.tsumakov.application.profile.education.exception.UserEducationPathNotFoundException;
import dev.tsumakov.application.profile.education.mapper.UserEducationPathDtoMapper;
import dev.tsumakov.domain.profile.education.model.UserEducationPath;
import dev.tsumakov.domain.profile.education.repository.UserEducationPathRepository;
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
class GetUserEducationPathByIdUseCaseImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.now();
  private static final UUID ID = UUID.randomUUID();

  @Mock
  private UserEducationPathRepository repository;
  @Mock
  private UserEducationPathDtoMapper mapper;

  private GetUserEducationPathByIdUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new GetUserEducationPathByIdUseCaseImpl(repository, mapper);
  }

  @Test
  void shouldReturnPathDtoWhenFound() {
    var path = new UserEducationPath(ID, "MIT", "Boston", Map.of("en", "BSc"), NOW, NOW.plusYears(4), true, NOW, NOW, 1L);
    var expectedDto = new UserEducationPathDto(ID, "MIT", "Boston", Map.of("en", "BSc"), NOW, NOW.plusYears(4), true, NOW, NOW);

    when(repository.findById(ID)).thenReturn(Optional.of(path));
    when(mapper.toDto(path)).thenReturn(expectedDto);

    var result = useCase.execute(ID);

    assertThat(result).isEqualTo(expectedDto);
  }

  @Test
  void shouldThrowNotFoundExceptionWhenPathMissing() {
    when(repository.findById(ID)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> useCase.execute(ID))
        .isInstanceOf(UserEducationPathNotFoundException.class)
        .hasMessage("User education path with id " + ID + " not found");

    verify(mapper, never()).toDto(org.mockito.ArgumentMatchers.any());
  }
}