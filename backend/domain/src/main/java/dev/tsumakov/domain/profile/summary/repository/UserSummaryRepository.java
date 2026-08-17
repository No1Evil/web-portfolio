package dev.tsumakov.domain.profile.summary.repository;

import dev.tsumakov.domain.profile.summary.model.UserSummary;
import dev.tsumakov.domain.shared.repository.CrudRepository;

public interface UserSummaryRepository extends CrudRepository<UserSummary, Integer> {

}
