package dev.tsumakov.domain.core.user.repository;

import dev.tsumakov.domain.core.user.model.User;
import dev.tsumakov.domain.shared.repository.CrudRepository;
import dev.tsumakov.domain.shared.repository.LockingRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

}
