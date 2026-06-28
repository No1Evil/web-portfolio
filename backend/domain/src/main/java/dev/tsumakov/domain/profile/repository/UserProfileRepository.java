package dev.tsumakov.domain.profile.repository;

import dev.tsumakov.domain.profile.model.UserProfile;
import java.util.Optional;
import java.util.UUID;

public interface UserProfileRepository {

  Optional<UserProfile> findByUserId(UUID userId);

  void save(UserProfile userProfile);
}
