package dev.tsumakov.infrastructure.profile.contact.persistence.repository;

import dev.tsumakov.domain.profile.contact.model.UserContact;
import dev.tsumakov.domain.profile.contact.repository.UserContactRepository;
import dev.tsumakov.infrastructure.profile.contact.persistence.mapper.UserContactEntityMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserContactRepositoryImpl implements UserContactRepository {

  private final UserContactSpringDataRepository repository;
  private final UserContactEntityMapper mapper;

  @Override
  public Optional<UserContact> findById(Integer identifier) {
    return repository.findById(identifier).map(mapper::toDomain);
  }

  @Override
  public boolean existsById(Integer identifier) {
    return repository.existsById(identifier);
  }

  @Override
  public List<UserContact> findAll() {
    return repository.findAll().stream().map(mapper::toDomain).toList();
  }

  @Override
  public void delete(Integer identifier) {
    repository.deleteById(identifier);
  }

  @Override
  public UserContact update(UserContact entity) {
    var entityToUpdate = mapper.toEntity(entity);
    var updatedEntity = repository.save(entityToUpdate);
    return mapper.toDomain(updatedEntity);
  }

  @Override
  public UserContact create(UserContact entity) {
    var entityToSave = mapper.toEntity(entity);
    entityToSave.setId(null);

    var savedEntity = repository.save(entityToSave);
    return mapper.toDomain(savedEntity);
  }
}
