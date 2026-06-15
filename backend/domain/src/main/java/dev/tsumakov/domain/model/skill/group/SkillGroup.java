package dev.tsumakov.domain.model.skill.group;

import dev.tsumakov.domain.model.AssetPath;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public record SkillGroup(
    UUID id,
    AssetPath icon,
    List<SkillGroupData> data
) {

  public SkillGroup {
    Objects.requireNonNull(id);

    data = List.copyOf(data);
  }

}
