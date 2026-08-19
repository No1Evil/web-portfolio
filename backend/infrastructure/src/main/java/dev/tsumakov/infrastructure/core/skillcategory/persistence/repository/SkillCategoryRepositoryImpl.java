package dev.tsumakov.infrastructure.core.skillcategory.persistence.repository;

import dev.tsumakov.domain.core.skillcategory.model.SkillCategory;
import dev.tsumakov.domain.core.skillcategory.repository.SkillCategoryRepository;
import dev.tsumakov.infrastructure.core.skillcategory.persistence.mapper.SkillCategoryEntityMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SkillCategoryRepositoryImpl implements SkillCategoryRepository {

  private final SkillCategorySpringDataRepository repository;
  private final SkillCategoryEntityMapper mapper;

  @Override
  public Optional<SkillCategory> findById(Integer identifier) {
    return repository.findById(identifier).map(mapper::toDomain);
  }

  @Override
  public boolean existsById(Integer identifier) {
    return repository.existsById(identifier);
  }

  @Override
  public List<SkillCategory> findAll() {
    return repository.findAll().stream().map(mapper::toDomain).toList();
  }

  @Override
  public void delete(Integer identifier) {
    repository.deleteById(identifier);
  }

  @Override
  public SkillCategory update(SkillCategory entity) {
    var entityToUpdate = mapper.toEntity(entity);
    var updatedEntity = repository.save(entityToUpdate);
    return mapper.toDomain(updatedEntity);
  }

  @Override
  public SkillCategory create(SkillCategory entity) {
    var entityToSave = mapper.toEntity(entity);
    entityToSave.setId(null);

    var savedEntity = repository.save(entityToSave);
    return mapper.toDomain(savedEntity);
  }
}
