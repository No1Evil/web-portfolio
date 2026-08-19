package dev.tsumakov.domain.core.skill.factory;

import dev.tsumakov.domain.core.skill.model.Skill;
import java.time.OffsetDateTime;

public class SkillFactory {

  public Skill createNew(Integer categoryId, String name, String iconUrl) {
    return new Skill(null, categoryId, name, iconUrl, OffsetDateTime.now(), OffsetDateTime.now(), 1L);
  }

}
