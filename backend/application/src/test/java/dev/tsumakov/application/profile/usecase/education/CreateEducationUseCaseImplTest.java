package dev.tsumakov.application.profile.usecase.education;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import dev.tsumakov.application.profile.dto.in.CreateEducationDto;
import dev.tsumakov.application.profile.mapper.EducationDtoMapperImpl;
import dev.tsumakov.domain.profile.repository.EducationRepository;
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
class CreateEducationUseCaseImplTest {

  @Mock
  private EducationRepository educationRepository;

  private CreateEducationUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new CreateEducationUseCaseImpl(educationRepository, new EducationDtoMapperImpl());
  }

  @Test
  void shouldCreateEducation() {
    var userId = UUID.randomUUID();
    var start = OffsetDateTime.of(2020, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
    var end = OffsetDateTime.of(2024, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
    var command = new CreateEducationDto(userId, Map.of("en", "MIT"), Map.of("en", "BSc"), start, end);

    var result = useCase.execute(command);

    assertThat(result.id()).isNull();
    assertThat(result.userId()).isEqualTo(userId);
    assertThat(result.institution()).containsEntry("en", "MIT");
    assertThat(result.degree()).containsEntry("en", "BSc");
    assertThat(result.startDate()).isEqualTo(start);
    assertThat(result.endDate()).isEqualTo(end);
    verify(educationRepository).save(any());
  }
}
