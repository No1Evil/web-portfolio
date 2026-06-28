package dev.tsumakov.domain.profile.repository;

import dev.tsumakov.domain.profile.model.UserContact;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserContactRepository {

  List<UserContact> findAllByUserId(UUID userId);

  Optional<UserContact> findById(Integer id);

  void save(UserContact userContact);

  void delete(UserContact userContact);

  void deleteById(Integer id);
}
