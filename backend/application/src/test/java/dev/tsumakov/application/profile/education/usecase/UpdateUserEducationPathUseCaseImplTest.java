package dev.tsumakov.application.profile.education.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.profile.education.dto.in.UpdateUserEducationPathDto;
import dev.tsumakov.application.profile.education.dto.outer.UserEducationPathDto;
import dev.tsumakov.application.profile.education.exception.UserEducationPathNotFoundException;
import dev.tsumakov.application.profile.education.mapper.UserEducationPathDtoMapper;
import dev.tsumakov.domain.profile.education.model.UserEducationPath;
import dev.tsumakov.domain.profile.education.repository.UserEducationPathRepository;
import dev.tsumakov.domain.shared.exception.DomainValidationException;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateUserEducationPathUseCaseImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.now();
  private static final UUID ID = UUID.randomUUID();

  @Mock
  private UserEducationPathRepository repository;
  @Mock
  private UserEducationPathDtoMapper mapper;

  private UpdateUserEducationPathUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new UpdateUserEducationPathUseCaseImpl(repository, mapper);
  }

  private UserEducationPath currentPath() {
    return new UserEducationPath(ID, "MIT", "Boston", Map.of("en", "BSc"), NOW, NOW.plusYears(4), false, NOW, NOW, 3L);
  }

  @Test
  void shouldThrowNotFoundExceptionWhenPathMissing() {
    when(repository.findById(ID)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> useCase.execute(new UpdateUserEducationPathDto(ID, null, null, null, null, null, null)))
        .isInstanceOf(UserEducationPathNotFoundException.class)
        .hasMessage("User education path with id " + ID + " not found");

    verify(repository, never()).update(any());
  }

  @Test
  void shouldUpdateAllProvidedFields() {
    var command = new UpdateUserEducationPathDto(ID, "Harvard", "Cambridge", Map.of("en", "MSc"), NOW.minusYears(1), NOW.plusYears(3), true);
    var current = currentPath();
    var saved = new UserEducationPath(ID, "Harvard", "Cambridge", Map.of("en", "MSc"), NOW.minusYears(1), NOW.plusYears(3), true, NOW, NOW, 3L);
    var expectedDto = new UserEducationPathDto(ID, "Harvard", "Cambridge", Map.of("en", "MSc"), NOW.minusYears(1), NOW.plusYears(3), true, NOW, NOW);

    when(repository.findById(ID)).thenReturn(Optional.of(current));
    when(repository.update(any())).thenReturn(saved);
    when(mapper.toDto(saved)).thenReturn(expectedDto);

    var result = useCase.execute(command);

    assertThat(result).isEqualTo(expectedDto);

    ArgumentCaptor<UserEducationPath> captor = ArgumentCaptor.forClass(UserEducationPath.class);
    verify(repository).update(captor.capture());
    var updated = captor.getValue();
    assertThat(updated.title()).isEqualTo("Harvard");
    assertThat(updated.location()).isEqualTo("Cambridge");
    assertThat(updated.description()).isEqualTo(Map.of("en", "MSc"));
    assertThat(updated.startDate()).isEqualTo(NOW.minusYears(1));
    assertThat(updated.endDate()).isEqualTo(NOW.plusYears(3));
    assertThat(updated.present()).isTrue();
  }

  @Test
  void shouldNotCallUpdateWhenNoFieldProvided() {
    var command = new UpdateUserEducationPathDto(ID, null, null, null, null, null, null);
    var current = currentPath();
    var expectedDto = new UserEducationPathDto(ID, "MIT", "Boston", Map.of("en", "BSc"), NOW, NOW.plusYears(4), false, NOW, NOW);

    when(repository.findById(ID)).thenReturn(Optional.of(current));
    when(mapper.toDto(current)).thenReturn(expectedDto);

    var result = useCase.execute(command);

    assertThat(result).isEqualTo(expectedDto);
    verify(repository, never()).update(any());
  }

  @Test
  void shouldPartiallyUpdateOnlyProvidedFields() {
    var command = new UpdateUserEducationPathDto(ID, null, "Seattle", null, null, null, null);
    var current = currentPath();

    when(repository.findById(ID)).thenReturn(Optional.of(current));
    when(repository.update(any())).thenReturn(current.updateLocation("Seattle"));
    when(mapper.toDto(any())).thenReturn(new UserEducationPathDto(ID, "MIT", "Seattle", Map.of("en", "BSc"), NOW, NOW.plusYears(4), false, NOW, NOW));

    useCase.execute(command);

    ArgumentCaptor<UserEducationPath> captor = ArgumentCaptor.forClass(UserEducationPath.class);
    verify(repository).update(captor.capture());
    var updated = captor.getValue();
    assertThat(updated.title()).isEqualTo("MIT");
    assertThat(updated.location()).isEqualTo("Seattle");
    assertThat(updated.present()).isFalse();
  }

  @Test
  void shouldPropagateDomainValidationExceptionWhenTitleIsBlank() {
    var command = new UpdateUserEducationPathDto(ID, "  ", null, null, null, null, null);

    when(repository.findById(ID)).thenReturn(Optional.of(currentPath()));

    assertThatThrownBy(() -> useCase.execute(command))
        .isInstanceOf(DomainValidationException.class);

    verify(repository, never()).update(any());
  }
}