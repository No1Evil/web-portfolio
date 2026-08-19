package dev.tsumakov.infrastructure.core.user.persistence.repository;

import dev.tsumakov.domain.core.user.model.User;
import dev.tsumakov.domain.core.user.repository.UserRepository;
import dev.tsumakov.infrastructure.core.user.persistence.mapper.UserEntityMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  private final UserSpringDataRepository repository;
  private final UserEntityMapper mapper;

  @Override
  public Optional<User> findById(Integer identifier) {
    return repository.findById(identifier).map(mapper::toDomain);
  }

  @Override
  public boolean existsById(Integer identifier) {
    return repository.existsById(identifier);
  }

  @Override
  public List<User> findAll() {
    return repository.findAll().stream().map(mapper::toDomain).toList();
  }

  @Override
  public void delete(Integer identifier) {
    repository.deleteById(identifier);
  }

  @Override
  public User update(User entity) {
    var entityToUpdate = mapper.toEntity(entity);
    var updatedEntity = repository.save(entityToUpdate);
    return mapper.toDomain(updatedEntity);
  }

  @Override
  public User create(User entity) {
    var entityToSave = mapper.toEntity(entity);
    entityToSave.setId(null);

    var savedEntity = repository.save(entityToSave);
    return mapper.toDomain(savedEntity);
  }

  @Override
  public Optional<User> findByUsername(String username) {
    return repository.findByUsername(username).map(mapper::toDomain);
  }
}
