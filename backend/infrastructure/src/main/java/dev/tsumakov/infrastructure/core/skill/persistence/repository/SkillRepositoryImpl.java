package dev.tsumakov.infrastructure.core.skill.persistence.repository;

import dev.tsumakov.domain.core.skill.model.Skill;
import dev.tsumakov.domain.core.skill.repository.SkillRepository;
import dev.tsumakov.infrastructure.core.skill.persistence.mapper.SkillEntityMapper;
import dev.tsumakov.infrastructure.core.skillcategory.persistence.repository.SkillCategorySpringDataRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SkillRepositoryImpl implements SkillRepository {

  private final SkillSpringDataRepository repository;
  private final SkillCategorySpringDataRepository skillCategoryRepository;
  private final SkillEntityMapper mapper;

  @Override
  public Optional<Skill> findById(Integer identifier) {
    return repository.findById(identifier).map(mapper::toDomain);
  }

  @Override
  public boolean existsById(Integer identifier) {
    return repository.existsById(identifier);
  }

  @Override
  public List<Skill> findAll() {
    return repository.findAll().stream().map(mapper::toDomain).toList();
  }

  @Override
  public void delete(Integer identifier) {
    repository.deleteById(identifier);
  }

  @Override
  public Skill create(Skill entity) {
    var entityToSave = mapper.toEntity(entity);
    entityToSave.setId(null);
    entityToSave.setSkillCategory(
      skillCategoryRepository.getReferenceById(entity.categoryId())
    );
    var savedEntity = repository.save(entityToSave);
    return mapper.toDomain(savedEntity);
  }

  @Override
  public Skill update(Skill entity) {
    var entityToUpdate = mapper.toEntity(entity);
    var updatedEntity = repository.save(entityToUpdate);
    return mapper.toDomain(updatedEntity);
  }
}
