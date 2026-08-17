package dev.tsumakov.domain.profile.education.repository;

import dev.tsumakov.domain.profile.education.model.UserEducationPath;
import dev.tsumakov.domain.shared.repository.CrudRepository;
import java.util.UUID;

public interface UserEducationPathRepository extends CrudRepository<UserEducationPath, UUID> {

}
