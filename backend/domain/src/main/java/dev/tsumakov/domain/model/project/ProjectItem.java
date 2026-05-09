package dev.tsumakov.domain.model.project;

import dev.tsumakov.domain.model.AssetPath;
import java.net.URI;
import java.util.List;
import java.util.Objects;

public record ProjectItem(
    AssetPath image,
    URI link,
    List<ProjectData> data,
    List<Skill> skills
) {

  public ProjectItem {
    data = List.copyOf(data);
    skills = List.copyOf(skills);
  }

  public record Skill(
      String name,
      boolean isPrimary
  ) {

    public Skill {
      Objects.requireNonNull(name);
    }
  }

}
