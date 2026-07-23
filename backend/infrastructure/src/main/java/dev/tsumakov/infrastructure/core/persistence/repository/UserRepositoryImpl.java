package dev.tsumakov.infrastructure.core.persistence.repository;

import dev.tsumakov.domain.core.model.User;
import dev.tsumakov.domain.core.repository.UserRepository;
import dev.tsumakov.infrastructure.core.persistence.entity.UserEntity;
import dev.tsumakov.infrastructure.core.persistence.mapper.UserMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  private final UserSpringDataRepository springDataRepository;
  private final UserMapper mapper;

  @Override
  public List<User> findAll() {
    return springDataRepository.findAll().stream().map(mapper::toDomain).toList();
  }

  @Override
  public User save(User user) {
    UserEntity saved = springDataRepository.save(mapper.toEntity(user));
    return mapper.toDomain(saved);
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return springDataRepository.findByEmail(email).map(mapper::toDomain);
  }

  @Override
  public void delete(User user) {
    springDataRepository.delete(mapper.toEntity(user));
  }

}
