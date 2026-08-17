package dev.tsumakov.application.profile.contact.port.in;

import dev.tsumakov.application.profile.contact.dto.outer.UserContactDto;

public interface GetUserContactByIdUseCase {

  UserContactDto execute(Integer userContactId);

}
