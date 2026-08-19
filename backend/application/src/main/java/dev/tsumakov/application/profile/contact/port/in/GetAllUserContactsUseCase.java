package dev.tsumakov.application.profile.contact.port.in;

import dev.tsumakov.application.profile.contact.dto.outer.UserContactDto;
import java.util.List;

public interface GetAllUserContactsUseCase {

  List<UserContactDto> execute();

}
