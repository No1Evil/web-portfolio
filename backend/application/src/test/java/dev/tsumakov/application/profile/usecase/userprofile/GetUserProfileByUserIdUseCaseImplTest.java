package dev.tsumakov.application.profile.usecase.userprofile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.profile.mapper.UserProfileDtoMapperImpl;
import dev.tsumakov.domain.profile.model.UserProfile;
import dev.tsumakov.domain.profile.repository.UserProfileRepository;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetUserProfileByUserIdUseCaseImplTest {

  @Mock
  private UserProfileRepository userProfileRepository;

  private GetUserProfileByUserIdUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new GetUserProfileByUserIdUseCaseImpl(userProfileRepository, new UserProfileDtoMapperImpl());
  }

  @Test
  void shouldReturnProfileWhenFound() {
    var userId = UUID.randomUUID();
    var profile = new UserProfile(userId, Map.of("en", "Engineer"), Map.of("en", "Bio"));

    when(userProfileRepository.findByUserId(userId)).thenReturn(Optional.of(profile));

    var result = useCase.execute(userId);

    assertThat(result).isPresent();
    assertThat(result.get().title()).containsEntry("en", "Engineer");
  }

  @Test
  void shouldReturnEmptyWhenNotFound() {
    var userId = UUID.randomUUID();

    when(userProfileRepository.findByUserId(userId)).thenReturn(Optional.empty());

    var result = useCase.execute(userId);

    assertThat(result).isEmpty();
  }
}
