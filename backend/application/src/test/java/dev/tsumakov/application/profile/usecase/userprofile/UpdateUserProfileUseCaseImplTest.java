package dev.tsumakov.application.profile.usecase.userprofile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import dev.tsumakov.application.profile.dto.in.UpdateUserProfileDto;
import dev.tsumakov.application.profile.mapper.UserProfileDtoMapperImpl;
import dev.tsumakov.domain.profile.repository.UserProfileRepository;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateUserProfileUseCaseImplTest {

  @Mock
  private UserProfileRepository userProfileRepository;

  private UpdateUserProfileUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new UpdateUserProfileUseCaseImpl(userProfileRepository, new UserProfileDtoMapperImpl());
  }

  @Test
  void shouldUpsertUserProfile() {
    var userId = UUID.randomUUID();
    var command = new UpdateUserProfileDto(userId, Map.of("en", "Engineer"), Map.of("en", "Bio"));

    var result = useCase.execute(command);

    assertThat(result.userId()).isEqualTo(userId);
    assertThat(result.title()).containsEntry("en", "Engineer");
    assertThat(result.description()).containsEntry("en", "Bio");
    verify(userProfileRepository).save(any());
  }
}
