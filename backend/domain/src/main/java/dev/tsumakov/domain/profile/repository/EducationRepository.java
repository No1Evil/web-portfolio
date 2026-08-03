package dev.tsumakov.domain.profile.repository;

import dev.tsumakov.domain.profile.model.Education;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EducationRepository {

  List<Education> findAllByUserId(UUID userId);

  Optional<Education> findByIdAndUserId(Integer id, UUID userId);

  Optional<Education> findById(Integer id);

  void save(Education education);

  void delete(Education education);

  void deleteById(Integer id);

  void deleteByIdAndUserId(Integer id, UUID userId);
}
