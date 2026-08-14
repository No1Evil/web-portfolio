package dev.tsumakov.infrastructure.portfolio.persistence.repository;

import dev.tsumakov.domain.portfolio.model.Skill;
import dev.tsumakov.domain.portfolio.repository.SkillRepository;
import dev.tsumakov.infrastructure.portfolio.persistence.mapper.SkillMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SkillRepositoryImpl implements SkillRepository {

  private final SkillSpringDataRepository springDataRepository;
  private final SkillMapper mapper;

  @Override
  public Optional<Skill> findById(Integer skillId) {
    return springDataRepository.findById(skillId).stream().map(mapper::toDomain).findFirst();
  }

  @Override
  public List<Skill> findAll() {
    return springDataRepository.findAll().stream().map(mapper::toDomain).toList();
  }

  @Override
  public List<Skill> findAllByCategory(Integer skillCategoryId) {
    return springDataRepository.findBySkillCategory_id(skillCategoryId).stream()
        .map(mapper::toDomain).toList();
  }

  @Override
  public Optional<Skill> findByName(String name) {
    return springDataRepository.findByName(name).stream()
        .map(mapper::toDomain).findFirst();
  }

  @Override
  public void save(Skill skill) {
    springDataRepository.save(mapper.toEntity(skill));
  }

  @Override
  public void delete(Skill skill) {
    springDataRepository.delete(mapper.toEntity(skill));
  }

  @Override
  public void deleteById(Integer id) {
    springDataRepository.deleteById(id);
  }
}
