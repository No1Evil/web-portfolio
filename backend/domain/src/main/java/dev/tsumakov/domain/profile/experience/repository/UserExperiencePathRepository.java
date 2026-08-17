package dev.tsumakov.domain.profile.experience.repository;

import dev.tsumakov.domain.profile.experience.model.UserExperiencePath;
import dev.tsumakov.domain.shared.repository.CrudRepository;
import java.util.UUID;

public interface UserExperiencePathRepository extends CrudRepository<UserExperiencePath, UUID> {

}
