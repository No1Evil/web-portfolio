package dev.tsumakov.domain.portfolio.repository;

import dev.tsumakov.domain.portfolio.model.UserProject;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface UserProjectRepository {

  List<UserProject> findAllByUserId(UUID userId);

  List<UserProject> findByIdAndUserId(Integer id, UUID userId);

  List<UserProject> findMatchingSkills(UUID userId, Set<Integer> skillsId);

  void save(UserProject userProject);

  void delete(UserProject userProject);

  void deleteByIdAndUserId(Integer id, UUID userId);
}
