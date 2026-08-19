package dev.tsumakov.infrastructure.profile.education.persistence.repository;

import dev.tsumakov.domain.profile.education.model.UserEducationPath;
import dev.tsumakov.domain.profile.education.repository.UserEducationPathRepository;
import dev.tsumakov.infrastructure.profile.education.persistence.mapper.UserEducationPathEntityMapper;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserEducationPathRepositoryImpl implements UserEducationPathRepository {

  private final UserEducationPathSpringDataRepository repository;
  private final UserEducationPathEntityMapper mapper;

  @Override
  public Optional<UserEducationPath> findById(UUID identifier) {
    return repository.findById(identifier).map(mapper::toDomain);
  }

  @Override
  public boolean existsById(UUID identifier) {
    return repository.existsById(identifier);
  }

  @Override
  public List<UserEducationPath> findAll() {
    return repository.findAll().stream().map(mapper::toDomain).toList();
  }

  @Override
  public void delete(UUID identifier) {
    repository.deleteById(identifier);
  }

  @Override
  public UserEducationPath update(UserEducationPath entity) {
    var entityToUpdate = mapper.toEntity(entity);
    var updatedEntity = repository.save(entityToUpdate);
    return mapper.toDomain(updatedEntity);
  }

  @Override
  public UserEducationPath create(UserEducationPath entity) {
    var entityToSave = mapper.toEntity(entity);
    entityToSave.setId(null);
    var savedEntity = repository.save(entityToSave);
    return mapper.toDomain(savedEntity);
  }
}
