package dev.tsumakov.domain.model.project;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public record ProjectPortfolio(
    UUID uuid,
    UUID userId,
    List<ProjectItem> projects
) {

  public ProjectPortfolio {
    Objects.requireNonNull(uuid);
    Objects.requireNonNull(userId);

    projects = List.copyOf(projects);
  }
}
