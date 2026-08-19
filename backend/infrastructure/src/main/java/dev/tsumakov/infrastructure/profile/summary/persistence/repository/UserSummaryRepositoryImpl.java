package dev.tsumakov.infrastructure.profile.summary.persistence.repository;

import dev.tsumakov.domain.profile.summary.model.UserSummary;
import dev.tsumakov.domain.profile.summary.repository.UserSummaryRepository;
import dev.tsumakov.infrastructure.profile.summary.persistence.mapper.UserSummaryEntityMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserSummaryRepositoryImpl implements UserSummaryRepository {

  private final UserSummarySpringDataRepository repository;
  private final UserSummaryEntityMapper mapper;

  @Override
  public Optional<UserSummary> findById(Integer identifier) {
    return repository.findById(identifier).map(mapper::toDomain);
  }

  @Override
  public boolean existsById(Integer identifier) {
    return repository.existsById(identifier);
  }

  @Override
  public List<UserSummary> findAll() {
    return repository.findAll().stream().map(mapper::toDomain).toList();
  }

  @Override
  public void delete(Integer identifier) {
    repository.deleteById(identifier);
  }

  @Override
  public UserSummary update(UserSummary entity) {
    var entityToUpdate = mapper.toEntity(entity);
    var updatedEntity = repository.save(entityToUpdate);
    return mapper.toDomain(updatedEntity);
  }

  @Override
  public UserSummary create(UserSummary entity) {
    var entityToSave = mapper.toEntity(entity);
    var savedEntity = repository.save(entityToSave);
    return mapper.toDomain(savedEntity);
  }
}
