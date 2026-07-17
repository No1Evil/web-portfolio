package dev.tsumakov.application.profile.port.in.usercontact;

import dev.tsumakov.application.profile.dto.api.UserContactDto;
import java.util.List;
import java.util.UUID;

public interface GetAllUserContactsByUserIdUseCase {
  List<UserContactDto> execute(UUID userId);
}
