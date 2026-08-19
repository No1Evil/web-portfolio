package dev.tsumakov.infrastructure.profile.experience.persistence.repository;

import dev.tsumakov.domain.profile.experience.model.UserExperiencePath;
import dev.tsumakov.domain.profile.experience.repository.UserExperiencePathRepository;
import dev.tsumakov.infrastructure.profile.experience.persistence.mapper.UserExperiencePathEntityMapper;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserExperiencePathRepositoryImpl implements UserExperiencePathRepository {

  private final UserExperiencePathSpringDataRepository repository;
  private final UserExperiencePathEntityMapper mapper;

  @Override
  public Optional<UserExperiencePath> findById(UUID identifier) {
    return repository.findById(identifier).map(mapper::toDomain);
  }

  @Override
  public boolean existsById(UUID identifier) {
    return repository.existsById(identifier);
  }

  @Override
  public List<UserExperiencePath> findAll() {
    return repository.findAll().stream().map(mapper::toDomain).toList();
  }

  @Override
  public void delete(UUID identifier) {
    repository.deleteById(identifier);
  }

  @Override
  public UserExperiencePath update(UserExperiencePath entity) {
    var entityToUpdate = mapper.toEntity(entity);
    var updatedEntity = repository.save(entityToUpdate);
    return mapper.toDomain(updatedEntity);
  }

  @Override
  public UserExperiencePath create(UserExperiencePath entity) {
    var entityToSave = mapper.toEntity(entity);
    entityToSave.setId(null);
    var savedEntity = repository.save(entityToSave);
    return mapper.toDomain(savedEntity);
  }
}
