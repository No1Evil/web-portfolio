package dev.tsumakov.infrastructure.core.persistence.repository;

import dev.tsumakov.domain.core.model.Role;
import dev.tsumakov.domain.core.repository.RoleRepository;
import dev.tsumakov.infrastructure.core.persistence.entity.RoleEntity;
import dev.tsumakov.infrastructure.core.persistence.mapper.RoleMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {

  private final RoleSpringDataRepository springDataRepository;
  private final RoleMapper mapper;

  @Override
  public List<Role> findAll() {
    return springDataRepository.findAll().stream().map(mapper::toDomain).toList();
  }

  @Override
  public Optional<Role> findByName(String name) {
    return springDataRepository.findByName(name).map(mapper::toDomain);
  }

  @Override
  public Role save(Role role) {
    RoleEntity saved = springDataRepository.save(mapper.toEntity(role));
    return mapper.toDomain(saved);
  }

  @Override
  public void delete(Role role) {
    springDataRepository.delete(mapper.toEntity(role));
  }
}
