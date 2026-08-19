package dev.tsumakov.application.profile.experience.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.profile.experience.dto.in.UpdateUserExperiencePathDto;
import dev.tsumakov.application.profile.experience.dto.outer.UserExperiencePathDto;
import dev.tsumakov.application.profile.experience.exception.UserExperiencePathNotFoundException;
import dev.tsumakov.application.profile.experience.mapper.UserExperiencePathDtoMapper;
import dev.tsumakov.domain.profile.experience.model.UserExperiencePath;
import dev.tsumakov.domain.profile.experience.repository.UserExperiencePathRepository;
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
class UpdateUserExperiencePathUseCaseImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.now();
  private static final UUID ID = UUID.randomUUID();

  @Mock
  private UserExperiencePathRepository repository;
  @Mock
  private UserExperiencePathDtoMapper mapper;

  private UpdateUserExperiencePathUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new UpdateUserExperiencePathUseCaseImpl(repository, mapper);
  }

  private UserExperiencePath currentPath() {
    return new UserExperiencePath(ID, "Google", "Software Engineer", "Mountain View", Map.of("en", "Backend"), NOW, NOW.plusYears(2), false, NOW, NOW, 3L);
  }

  @Test
  void shouldThrowNotFoundExceptionWhenPathMissing() {
    when(repository.findById(ID)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> useCase.execute(new UpdateUserExperiencePathDto(ID, null, null, null, null, null, null, null)))
        .isInstanceOf(UserExperiencePathNotFoundException.class)
        .hasMessage("User experience path with id " + ID + " not found");

    verify(repository, never()).update(any());
  }

  @Test
  void shouldUpdateAllProvidedFields() {
    var command = new UpdateUserExperiencePathDto(ID, "Meta", "Senior Engineer", "London", Map.of("en", "Distributed Systems"), NOW.minusYears(1), NOW.plusYears(1), true);
    var current = currentPath();
    var saved = new UserExperiencePath(ID, "Meta", "Senior Engineer", "London", Map.of("en", "Distributed Systems"), NOW.minusYears(1), NOW.plusYears(1), true, NOW, NOW, 3L);
    var expectedDto = new UserExperiencePathDto(ID, "Meta", "Senior Engineer", "London", Map.of("en", "Distributed Systems"), NOW.minusYears(1), NOW.plusYears(1), true, NOW, NOW);

    when(repository.findById(ID)).thenReturn(Optional.of(current));
    when(repository.update(any())).thenReturn(saved);
    when(mapper.toDto(saved)).thenReturn(expectedDto);

    var result = useCase.execute(command);

    assertThat(result).isEqualTo(expectedDto);

    ArgumentCaptor<UserExperiencePath> captor = ArgumentCaptor.forClass(UserExperiencePath.class);
    verify(repository).update(captor.capture());
    var updated = captor.getValue();
    assertThat(updated.title()).isEqualTo("Meta");
    assertThat(updated.companyName()).isEqualTo("Senior Engineer");
    assertThat(updated.location()).isEqualTo("London");
    assertThat(updated.description()).isEqualTo(Map.of("en", "Distributed Systems"));
    assertThat(updated.startDate()).isEqualTo(NOW.minusYears(1));
    assertThat(updated.endDate()).isEqualTo(NOW.plusYears(1));
    assertThat(updated.present()).isTrue();
  }

  @Test
  void shouldNotCallUpdateWhenNoFieldProvided() {
    var command = new UpdateUserExperiencePathDto(ID, null, null, null, null, null, null, null);
    var current = currentPath();
    var expectedDto = new UserExperiencePathDto(ID, "Google", "Software Engineer", "Mountain View", Map.of("en", "Backend"), NOW, NOW.plusYears(2), false, NOW, NOW);

    when(repository.findById(ID)).thenReturn(Optional.of(current));
    when(mapper.toDto(current)).thenReturn(expectedDto);

    var result = useCase.execute(command);

    assertThat(result).isEqualTo(expectedDto);
    verify(repository, never()).update(any());
  }

  @Test
  void shouldPartiallyUpdateOnlyProvidedFields() {
    var command = new UpdateUserExperiencePathDto(ID, null, "Staff Engineer", null, null, null, null, null);
    var current = currentPath();

    when(repository.findById(ID)).thenReturn(Optional.of(current));
    when(repository.update(any())).thenReturn(current.updateCompanyName("Staff Engineer"));
    when(mapper.toDto(any())).thenReturn(new UserExperiencePathDto(ID, "Google", "Staff Engineer", "Mountain View", Map.of("en", "Backend"), NOW, NOW.plusYears(2), false, NOW, NOW));

    useCase.execute(command);

    ArgumentCaptor<UserExperiencePath> captor = ArgumentCaptor.forClass(UserExperiencePath.class);
    verify(repository).update(captor.capture());
    var updated = captor.getValue();
    assertThat(updated.title()).isEqualTo("Google");
    assertThat(updated.companyName()).isEqualTo("Staff Engineer");
    assertThat(updated.location()).isEqualTo("Mountain View");
    assertThat(updated.present()).isFalse();
  }

  @Test
  void shouldPropagateDomainValidationExceptionWhenTitleIsBlank() {
    var command = new UpdateUserExperiencePathDto(ID, "  ", null, null, null, null, null, null);

    when(repository.findById(ID)).thenReturn(Optional.of(currentPath()));

    assertThatThrownBy(() -> useCase.execute(command))
        .isInstanceOf(DomainValidationException.class);

    verify(repository, never()).update(any());
  }
}