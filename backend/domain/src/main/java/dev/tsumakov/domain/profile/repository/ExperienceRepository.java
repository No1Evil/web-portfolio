package dev.tsumakov.domain.profile.repository;

import dev.tsumakov.domain.profile.model.Experience;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExperienceRepository {

  List<Experience> findAllByUserId(UUID userId);

  Optional<Experience> findById(Integer id);

  void save(Experience experience);

  void delete(Experience experience);

  void deleteById(Integer id);

  void deleteByIdAndUserId(Integer id, UUID userId);
}
