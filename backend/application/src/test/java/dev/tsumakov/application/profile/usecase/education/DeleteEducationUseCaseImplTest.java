package dev.tsumakov.application.profile.usecase.education;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.domain.profile.model.Education;
import dev.tsumakov.domain.profile.repository.EducationRepository;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteEducationUseCaseImplTest {

  @Mock
  private EducationRepository educationRepository;

  private static final OffsetDateTime SOME_DATE = OffsetDateTime.of(2020, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);

  private DeleteEducationUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new DeleteEducationUseCaseImpl(educationRepository);
  }

  @Test
  void shouldDeleteWhenFoundAndOwned() {
    var userId = UUID.randomUUID();
    var education = new Education(1, userId, Map.of("en", "MIT"), Map.of("en", "BSc"), SOME_DATE, SOME_DATE);

    when(educationRepository.findById(1)).thenReturn(Optional.of(education));

    useCase.execute(1, userId);

    verify(educationRepository).delete(education);
  }

  @Test
  void shouldNotDeleteWhenDifferentOwner() {
    var userId = UUID.randomUUID();
    var otherUserId = UUID.randomUUID();
    var education = new Education(1, otherUserId, Map.of("en", "MIT"), Map.of("en", "BSc"), SOME_DATE, SOME_DATE);

    when(educationRepository.findById(1)).thenReturn(Optional.of(education));

    useCase.execute(1, userId);

    verify(educationRepository).findById(1);
  }

  @Test
  void shouldNotDeleteWhenNotFound() {
    when(educationRepository.findById(99)).thenReturn(Optional.empty());

    useCase.execute(99, UUID.randomUUID());

    verify(educationRepository).findById(99);
  }
}
