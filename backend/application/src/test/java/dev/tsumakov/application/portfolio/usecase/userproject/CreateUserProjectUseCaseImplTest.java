package dev.tsumakov.application.portfolio.usecase.userproject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.portfolio.dto.in.CreateUserProjectDto;
import dev.tsumakov.application.portfolio.mapper.UserProjectDtoMapperImpl;
import dev.tsumakov.domain.portfolio.model.Skill;
import dev.tsumakov.domain.portfolio.repository.SkillRepository;
import dev.tsumakov.domain.portfolio.repository.UserProjectRepository;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateUserProjectUseCaseImplTest {

  @Mock
  private UserProjectRepository userProjectRepository;

  @Mock
  private SkillRepository skillRepository;

  private CreateUserProjectUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new CreateUserProjectUseCaseImpl(userProjectRepository, skillRepository,
        new UserProjectDtoMapperImpl());
  }

  @Test
  void shouldCreateProjectWithAllResolvedSkills() {
    var userId = UUID.randomUUID();
    var command = new CreateUserProjectDto(userId, Map.of("en", "My Project"),
        Map.of("en", "Description"), Set.of(1, 2), true, "url", "img");

    when(skillRepository.findById(1)).thenReturn(Optional.of(new Skill(1, 10, "Java")));
    when(skillRepository.findById(2)).thenReturn(Optional.of(new Skill(2, 10, "Kotlin")));

    var result = useCase.execute(command);

    assertThat(result.id()).isNull();
    assertThat(result.userId()).isEqualTo(userId);
    assertThat(result.title()).containsEntry("en", "My Project");
    assertThat(result.skills()).hasSize(2);
    assertThat(result.skills()).extracting("name").containsExactlyInAnyOrder("Java", "Kotlin");
    assertThat(result.isFeatured()).isTrue();
    verify(userProjectRepository).save(any());
  }

  @Test
  void shouldSilentlyDropInvalidSkillIds() {
    var userId = UUID.randomUUID();
    var command = new CreateUserProjectDto(userId, Map.of("en", "My Project"),
        Map.of("en", "Description"), Set.of(1, 99), true, "url", "img");

    when(skillRepository.findById(1)).thenReturn(Optional.of(new Skill(1, 10, "Java")));
    when(skillRepository.findById(99)).thenReturn(Optional.empty());

    var result = useCase.execute(command);

    assertThat(result.skills()).hasSize(1);
    assertThat(result.skills().getFirst().name()).isEqualTo("Java");
  }

  @Test
  void shouldDefaultIsFeaturedToFalseWhenNull() {
    var userId = UUID.randomUUID();
    var command = new CreateUserProjectDto(userId, Map.of("en", "My Project"),
        Map.of("en", "Description"), Set.of(1), null, null, null);

    when(skillRepository.findById(1)).thenReturn(Optional.of(new Skill(1, 10, "Java")));

    var result = useCase.execute(command);

    assertThat(result.isFeatured()).isFalse();
  }
}
