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
class GetUserProjectByIdUseCaseImplTest {

  @Mock
  private UserProjectRepository userProjectRepository;

  private GetUserProjectByIdUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new GetUserProjectByIdUseCaseImpl(userProjectRepository, new UserProjectDtoMapperImpl());
  }

  @Test
  void shouldReturnProjectWhenFound() {
    var userId = UUID.randomUUID();
    var project = new UserProject(1, userId, Map.of("en", "Project"), Map.of("en", "Desc"),
        Set.of(new Skill(1, 10, "Java")), true, "url", "img");

    when(userProjectRepository.findByIdAndUserId(1, userId)).thenReturn(List.of(project));

    var result = useCase.execute(1, userId);

    assertThat(result).isPresent();
    assertThat(result.get().title()).containsEntry("en", "Project");
    assertThat(result.get().skills()).hasSize(1);
  }

  @Test
  void shouldReturnEmptyWhenNotFound() {
    var userId = UUID.randomUUID();

    when(userProjectRepository.findByIdAndUserId(1, userId)).thenReturn(List.of());

    var result = useCase.execute(1, userId);

    assertThat(result).isEmpty();
  }
}
