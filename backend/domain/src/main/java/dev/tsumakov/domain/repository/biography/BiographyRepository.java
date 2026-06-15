package dev.tsumakov.domain.repository.biography;

import dev.tsumakov.domain.model.biography.Biography;
import dev.tsumakov.domain.model.biography.BiographyData;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

public interface BiographyRepository {

  Optional<Biography> findById(UUID id);

  Optional<BiographyData> findDataByUserId(UUID userId);

  Optional<BiographyData> findDataByUserId(UUID userId, Locale locale);

  void save(Biography biography);

  void delete(UUID id);
}
