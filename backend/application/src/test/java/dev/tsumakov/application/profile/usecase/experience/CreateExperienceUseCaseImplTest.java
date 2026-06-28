package dev.tsumakov.application.profile.usecase.experience;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import dev.tsumakov.application.profile.dto.in.CreateExperienceDto;
import dev.tsumakov.application.profile.mapper.ExperienceDtoMapperImpl;
import dev.tsumakov.domain.profile.repository.ExperienceRepository;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateExperienceUseCaseImplTest {

  @Mock
  private ExperienceRepository experienceRepository;

  private CreateExperienceUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new CreateExperienceUseCaseImpl(experienceRepository, new ExperienceDtoMapperImpl());
  }

  @Test
  void shouldCreateExperience() {
    var userId = UUID.randomUUID();
    var start = OffsetDateTime.of(2020, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
    var end = OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
    var command = new CreateExperienceDto(userId, "Acme", "Engineer", Map.of("en", "dev"), start, end);

    var result = useCase.execute(command);

    assertThat(result.id()).isNull();
    assertThat(result.userId()).isEqualTo(userId);
    assertThat(result.company()).isEqualTo("Acme");
    assertThat(result.position()).isEqualTo("Engineer");
    assertThat(result.description()).containsEntry("en", "dev");
    assertThat(result.startDate()).isEqualTo(start);
    assertThat(result.endDate()).isEqualTo(end);
    verify(experienceRepository).save(any());
  }
}
