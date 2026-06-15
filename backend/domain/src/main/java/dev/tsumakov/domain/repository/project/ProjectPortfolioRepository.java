package dev.tsumakov.domain.repository.project;

import dev.tsumakov.domain.model.project.ProjectItem;
import dev.tsumakov.domain.model.project.ProjectPortfolio;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

public interface ProjectPortfolioRepository {
  Optional<ProjectPortfolio> findById(UUID id);

  List<ProjectItem> findByUserId(UUID userId);

  List<ProjectItem> findLocalizedByUserId(UUID userId, Locale locale);

  void save(ProjectPortfolio portfolio);

  void delete(UUID id);
}
