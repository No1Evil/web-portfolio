package dev.tsumakov.application.profile.summary.port.in;

import dev.tsumakov.application.profile.summary.dto.outer.UserSummaryDto;

public interface GetUserSummaryUseCase {
  UserSummaryDto execute();
}
