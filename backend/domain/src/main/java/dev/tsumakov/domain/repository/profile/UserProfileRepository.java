package dev.tsumakov.domain.repository.profile;

import dev.tsumakov.domain.model.profile.UserProfile;
import java.util.Optional;
import java.util.UUID;

public interface UserProfileRepository {

  Optional<UserProfile> findByUserId(UUID userId);

  void save(UserProfile userProfile);
}
