package dev.tsumakov.application.profile.contact.port.in;

import dev.tsumakov.application.profile.contact.dto.in.UpdateUserContactDto;
import dev.tsumakov.application.profile.contact.dto.outer.UserContactDto;

public interface UpdateUserContactUseCase {

  UserContactDto execute(UpdateUserContactDto command);

}
