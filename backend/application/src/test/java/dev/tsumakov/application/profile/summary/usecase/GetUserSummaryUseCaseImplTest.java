package dev.tsumakov.application.profile.summary.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.profile.summary.dto.outer.UserSummaryDto;
import dev.tsumakov.application.profile.summary.exception.UserSummaryNotFoundException;
import dev.tsumakov.application.profile.summary.mapper.UserSummaryDtoMapper;
import dev.tsumakov.domain.profile.summary.model.UserSummary;
import dev.tsumakov.domain.profile.summary.repository.UserSummaryRepository;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetUserSummaryUseCaseImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.now();

  @Mock
  private UserSummaryRepository repository;
  @Mock
  private UserSummaryDtoMapper mapper;

  private GetUserSummaryUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new GetUserSummaryUseCaseImpl(repository, mapper);
  }

  @Test
  void shouldReturnSummaryForHardcodedUserId() {
    var summary = new UserSummary(1, "John", "Doe", "Senior", Map.of("en", "Bio"), "hero.svg", NOW, NOW, 1L);
    var expectedDto = new UserSummaryDto(1, "John", "Doe", "Senior", Map.of("en", "Bio"), "hero.svg", NOW, NOW);

    when(repository.findById(1)).thenReturn(Optional.of(summary));
    when(mapper.toDto(summary)).thenReturn(expectedDto);

    var result = useCase.execute();

    assertThat(result).isEqualTo(expectedDto);
    verify(repository).findById(1);
  }

  @Test
  void shouldThrowNotFoundExceptionWhenSummaryMissing() {
    when(repository.findById(1)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> useCase.execute())
        .isInstanceOf(UserSummaryNotFoundException.class)
        .hasMessage("User summary with id 1 not found");

    verify(mapper, never()).toDto(org.mockito.ArgumentMatchers.any());
  }
}