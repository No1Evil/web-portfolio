package dev.tsumakov.domain.shared.repository;

import java.util.Optional;

public interface LockingRepository<E, ID> {

  Optional<E> findByIdLocking(ID identifier);

}
