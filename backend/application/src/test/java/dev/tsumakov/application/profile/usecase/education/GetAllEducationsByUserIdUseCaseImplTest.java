package dev.tsumakov.application.profile.usecase.education;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.profile.mapper.EducationDtoMapperImpl;
import dev.tsumakov.domain.profile.model.Education;
import dev.tsumakov.domain.profile.repository.EducationRepository;
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
class GetAllEducationsByUserIdUseCaseImplTest {

  @Mock
  private EducationRepository educationRepository;

  private static final OffsetDateTime SOME_DATE = OffsetDateTime.of(2020, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);

  private GetAllEducationsByUserIdUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new GetAllEducationsByUserIdUseCaseImpl(educationRepository, new EducationDtoMapperImpl());
  }

  @Test
  void shouldReturnEducationsForUser() {
    var userId = UUID.randomUUID();
    var educations = List.of(
        new Education(1, userId, Map.of("en", "MIT"), Map.of("en", "BSc"), SOME_DATE, SOME_DATE),
        new Education(2, userId, Map.of("en", "Stanford"), Map.of("en", "MSc"), SOME_DATE, SOME_DATE)
    );

    when(educationRepository.findAllByUserId(userId)).thenReturn(educations);

    var result = useCase.execute(userId);

    assertThat(result).hasSize(2);
    assertThat(result).extracting("institution").extracting(m -> ((Map<String, String>) m).get("en"))
        .containsExactly("MIT", "Stanford");
  }

  @Test
  void shouldReturnEmptyListWhenNoEducations() {
    var userId = UUID.randomUUID();

    when(educationRepository.findAllByUserId(userId)).thenReturn(List.of());

    var result = useCase.execute(userId);

    assertThat(result).isEmpty();
  }
}
