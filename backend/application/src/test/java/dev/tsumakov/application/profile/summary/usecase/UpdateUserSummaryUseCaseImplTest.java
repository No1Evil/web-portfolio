package dev.tsumakov.application.profile.summary.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.profile.summary.dto.in.UpdateUserSummaryDto;
import dev.tsumakov.application.profile.summary.dto.outer.UserSummaryDto;
import dev.tsumakov.application.profile.summary.exception.UserSummaryNotFoundException;
import dev.tsumakov.application.profile.summary.mapper.UserSummaryDtoMapper;
import dev.tsumakov.domain.profile.summary.model.UserSummary;
import dev.tsumakov.domain.profile.summary.repository.UserSummaryRepository;
import dev.tsumakov.domain.shared.exception.DomainValidationException;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateUserSummaryUseCaseImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.now();

  @Mock
  private UserSummaryRepository repository;
  @Mock
  private UserSummaryDtoMapper mapper;

  private UpdateUserSummaryUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new UpdateUserSummaryUseCaseImpl(repository, mapper);
  }

  private UserSummary currentSummary() {
    return new UserSummary(1, "John", "Doe", "Senior", Map.of("en", "Bio"), "hero.svg", NOW, NOW, 3L);
  }

  @Test
  void shouldThrowNotFoundExceptionWhenSummaryMissing() {
    when(repository.findById(1)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> useCase.execute(
        new UpdateUserSummaryDto(null, null, null, null, null, null, null)))
        .isInstanceOf(UserSummaryNotFoundException.class)
        .hasMessage("User summary with id 1 not found");

    verify(repository, never()).update(any());
  }

  @Test
  void shouldUpdateAllProvidedFieldsWithIncrementedVersion() {
    var command = new UpdateUserSummaryDto("Jane", "Smith", "Lead", Map.of("en", "New bio"), "new-hero.svg", null, null);
    var saved = new UserSummary(1, "Jane", "Smith", "Lead", Map.of("en", "New bio"), "new-hero.svg", NOW, NOW, 4L);
    var expectedDto = new UserSummaryDto(1, "Jane", "Smith", "Lead", Map.of("en", "New bio"), "new-hero.svg", NOW, NOW);

    when(repository.findById(1)).thenReturn(Optional.of(currentSummary()));
    when(repository.update(any())).thenReturn(saved);
    when(mapper.toDto(saved)).thenReturn(expectedDto);

    var result = useCase.execute(command);

    assertThat(result).isEqualTo(expectedDto);

    ArgumentCaptor<UserSummary> captor = ArgumentCaptor.forClass(UserSummary.class);
    verify(repository).update(captor.capture());
    var updated = captor.getValue();
    assertThat(updated.firstName()).isEqualTo("Jane");
    assertThat(updated.lastName()).isEqualTo("Smith");
    assertThat(updated.proficiency()).isEqualTo("Lead");
    assertThat(updated.description()).isEqualTo(Map.of("en", "New bio"));
    assertThat(updated.heroImageUrl()).isEqualTo("new-hero.svg");
    assertThat(updated.version()).isEqualTo(4L);
  }

  @Test
  void shouldNotCallUpdateWhenNoFieldProvided() {
    var command = new UpdateUserSummaryDto(null, null, null, null, null, null, null);
    var current = currentSummary();
    var expectedDto = new UserSummaryDto(1, "John", "Doe", "Senior", Map.of("en", "Bio"), "hero.svg", NOW, NOW);

    when(repository.findById(1)).thenReturn(Optional.of(current));
    when(mapper.toDto(current)).thenReturn(expectedDto);

    var result = useCase.execute(command);

    assertThat(result).isEqualTo(expectedDto);
    verify(repository, never()).update(any());
  }

  @Test
  void shouldPartiallyUpdateOnlyProvidedFields() {
    var command = new UpdateUserSummaryDto(null, "Smith", null, null, null, null, null);
    var current = currentSummary();

    when(repository.findById(1)).thenReturn(Optional.of(current));
    when(repository.update(any())).thenReturn(current.updateLastName("Smith"));
    when(mapper.toDto(any())).thenReturn(new UserSummaryDto(1, "John", "Smith", "Senior", Map.of("en", "Bio"), "hero.svg", NOW, NOW));

    useCase.execute(command);

    ArgumentCaptor<UserSummary> captor = ArgumentCaptor.forClass(UserSummary.class);
    verify(repository).update(captor.capture());
    var updated = captor.getValue();
    assertThat(updated.firstName()).isEqualTo("John");
    assertThat(updated.lastName()).isEqualTo("Smith");
    assertThat(updated.proficiency()).isEqualTo("Senior");
    assertThat(updated.version()).isEqualTo(4L);
  }

  @Test
  void shouldPropagateDomainValidationExceptionWhenFirstNameIsBlank() {
    var command = new UpdateUserSummaryDto("  ", null, null, null, null, null, null);

    when(repository.findById(1)).thenReturn(Optional.of(currentSummary()));

    assertThatThrownBy(() -> useCase.execute(command))
        .isInstanceOf(DomainValidationException.class);

    verify(repository, never()).update(any());
  }
}