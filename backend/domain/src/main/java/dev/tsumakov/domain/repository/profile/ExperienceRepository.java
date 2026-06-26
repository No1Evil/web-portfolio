package dev.tsumakov.domain.repository.profile;

import dev.tsumakov.domain.model.profile.Experience;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExperienceRepository {

  List<Experience> findAllByUserId(UUID userId);

  Optional<Experience> findById(Integer id);

  void save(Experience experience);

  void delete(Experience experience);

  void deleteById(Integer id);
}
