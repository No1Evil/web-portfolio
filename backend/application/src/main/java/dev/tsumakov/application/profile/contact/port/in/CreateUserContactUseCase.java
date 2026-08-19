package dev.tsumakov.application.profile.contact.port.in;

import dev.tsumakov.application.profile.contact.dto.in.CreateUserContactDto;
import dev.tsumakov.application.profile.contact.dto.outer.UserContactDto;

public interface CreateUserContactUseCase {

  UserContactDto execute(CreateUserContactDto command);

}
