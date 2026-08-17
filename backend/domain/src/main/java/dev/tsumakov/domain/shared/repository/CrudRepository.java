package dev.tsumakov.domain.shared.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<E, ID> {

  Optional<E> findById(ID identifier);

  boolean existsById(ID identifier);

  List<E> findAll();

  void delete(ID identifier);

  E update(E entity);

  E create(E entity);
}
