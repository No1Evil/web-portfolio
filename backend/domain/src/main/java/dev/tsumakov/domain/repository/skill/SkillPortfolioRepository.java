package dev.tsumakov.domain.repository.skill;

import dev.tsumakov.domain.model.skill.SkillPortfolio;
import java.util.Optional;
import java.util.UUID;

public interface SkillPortfolioRepository {

  Optional<SkillPortfolio> findById(UUID id);

  Optional<SkillPortfolio> findByUserId(UUID userId);

  void save(SkillPortfolio portfolio);

  void delete(UUID id);
}
