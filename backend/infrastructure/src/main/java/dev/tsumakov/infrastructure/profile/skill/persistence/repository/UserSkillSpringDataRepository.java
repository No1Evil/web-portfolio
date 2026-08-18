package dev.tsumakov.infrastructure.profile.skill.persistence.repository;

import dev.tsumakov.infrastructure.core.skill.persistence.entity.SkillEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSkillSpringDataRepository extends JpaRepository<SkillEntity, Integer> {

  @Modifying
  @Query(value = """
      insert into profile.user_skills (user_id, skill_id)
      values (:userId, :skillId)
      on conflict do nothing
      """, nativeQuery = true)
  void addSkill(@Param("userId") Integer userId, @Param("skillId") Integer skillId);


  @Modifying
  @Query(value = """
      delete from profile.user_skills
      where user_id = :userId and skill_id = :skillId
      """, nativeQuery = true)
  void removeSkill(@Param("userId") Integer userId, @Param("skillId") Integer skillId);

  @Query(value = """
      select s.* from core.skills s
      join profile.user_skills us on s.id = us.skill_id
      where us.user_id = :userId
      """, nativeQuery = true)
  List<SkillEntity> findAllByUserId(@Param("userId") Integer userId);

  @Query(value = """
      select count(*) > 0
      from profile.user_skills
      where user_id = :userId and skill_id = :skillId
      """, nativeQuery = true)
  boolean exists(@Param("userId") Integer userId, @Param("skillId") Integer skillId);
}
