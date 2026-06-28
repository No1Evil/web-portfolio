package dev.tsumakov.application.profile.port.in.usercontact;

import dev.tsumakov.application.profile.dto.api.UserContactDto;
import dev.tsumakov.application.profile.dto.in.CreateUserContactDto;

public interface CreateUserContactUseCase {
  UserContactDto execute(CreateUserContactDto command);
}
