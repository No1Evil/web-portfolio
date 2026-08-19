package dev.tsumakov.domain.core.skillcategory.factory;

import dev.tsumakov.domain.core.skillcategory.model.SkillCategory;
import java.time.OffsetDateTime;

public class SkillCategoryFactory {

  public SkillCategory createNew(String name, String iconUrl) {
    return new SkillCategory(null, name, iconUrl, 1L, OffsetDateTime.now(), OffsetDateTime.now());
  }

}
