package dev.tsumakov.application.profile.usecase.experience;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.profile.mapper.ExperienceDtoMapperImpl;
import dev.tsumakov.domain.profile.model.Experience;
import dev.tsumakov.domain.profile.repository.ExperienceRepository;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetAllExperiencesByUserIdUseCaseImplTest {

  @Mock
  private ExperienceRepository experienceRepository;

  private static final OffsetDateTime SOME_DATE = OffsetDateTime.of(2020, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);

  private GetAllExperiencesByUserIdUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new GetAllExperiencesByUserIdUseCaseImpl(experienceRepository,
        new ExperienceDtoMapperImpl());
  }

  @Test
  void shouldReturnExperiencesForUser() {
    var userId = UUID.randomUUID();
    var experiences = List.of(
        new Experience(1, userId, "Acme", "Engineer", Map.of("en", "dev"), SOME_DATE, SOME_DATE),
        new Experience(2, userId, "Beta", "Sr Engineer", Map.of("en", "lead"), SOME_DATE, SOME_DATE)
    );

    when(experienceRepository.findAllByUserId(userId)).thenReturn(experiences);

    var result = useCase.execute(userId);

    assertThat(result).hasSize(2);
    assertThat(result).extracting("company").containsExactly("Acme", "Beta");
  }

  @Test
  void shouldReturnEmptyListWhenNoExperiences() {
    var userId = UUID.randomUUID();

    when(experienceRepository.findAllByUserId(userId)).thenReturn(List.of());

    var result = useCase.execute(userId);

    assertThat(result).isEmpty();
  }
}
