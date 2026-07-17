package dev.tsumakov.application.portfolio.usecase.userproject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.portfolio.mapper.UserProjectDtoMapperImpl;
import dev.tsumakov.domain.portfolio.model.Skill;
import dev.tsumakov.domain.portfolio.model.UserProject;
import dev.tsumakov.domain.portfolio.repository.UserProjectRepository;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetUserProjectsByUserIdUseCaseImplTest {

  @Mock
  private UserProjectRepository userProjectRepository;

  private GetUserProjectsByUserIdUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new GetUserProjectsByUserIdUseCaseImpl(userProjectRepository,
        new UserProjectDtoMapperImpl());
  }

  @Test
  void shouldReturnProjectsForUser() {
    var userId = UUID.randomUUID();
    var projects = List.of(
        new UserProject(1, userId, Map.of("en", "Project A"), Map.of("en", "Desc A"),
            Set.of(new Skill(1, 10, "Java")), true, null, null),
        new UserProject(2, userId, Map.of("en", "Project B"), Map.of("en", "Desc B"),
            Set.of(new Skill(2, 10, "Kotlin")), false, null, null)
    );

    when(userProjectRepository.findAllByUserId(userId)).thenReturn(projects);

    var result = useCase.execute(userId);

    assertThat(result).hasSize(2);
    assertThat(result).extracting("title").extracting(t -> ((Map<String, String>) t).get("en"))
        .containsExactly("Project A", "Project B");
  }

  @Test
  void shouldReturnEmptyListWhenNoProjects() {
    var userId = UUID.randomUUID();

    when(userProjectRepository.findAllByUserId(userId)).thenReturn(List.of());

    var result = useCase.execute(userId);

    assertThat(result).isEmpty();
  }
}
