package dev.tsumakov.application.profile.usecase.experience;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.domain.profile.model.Experience;
import dev.tsumakov.domain.profile.repository.ExperienceRepository;
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
class DeleteExperienceUseCaseImplTest {

  @Mock
  private ExperienceRepository experienceRepository;

  private static final OffsetDateTime SOME_DATE = OffsetDateTime.of(2020, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);

  private DeleteExperienceUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new DeleteExperienceUseCaseImpl(experienceRepository);
  }

  @Test
  void shouldDeleteWhenFoundAndOwned() {
    var userId = UUID.randomUUID();
    var experience = new Experience(1, userId, "Acme", "Engineer", Map.of("en", "dev"), SOME_DATE, SOME_DATE);

    when(experienceRepository.findById(1)).thenReturn(Optional.of(experience));

    useCase.execute(1, userId);

    verify(experienceRepository).delete(experience);
  }

  @Test
  void shouldNotDeleteWhenDifferentOwner() {
    var userId = UUID.randomUUID();
    var otherUserId = UUID.randomUUID();
    var experience = new Experience(1, otherUserId, "Acme", "Engineer", Map.of("en", "dev"), SOME_DATE, SOME_DATE);

    when(experienceRepository.findById(1)).thenReturn(Optional.of(experience));

    useCase.execute(1, userId);

    verify(experienceRepository).findById(1);
  }

  @Test
  void shouldNotDeleteWhenNotFound() {
    when(experienceRepository.findById(99)).thenReturn(Optional.empty());

    useCase.execute(99, UUID.randomUUID());

    verify(experienceRepository).findById(99);
  }
}
