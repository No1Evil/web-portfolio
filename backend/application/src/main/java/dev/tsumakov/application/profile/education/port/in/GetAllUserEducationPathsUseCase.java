package dev.tsumakov.application.profile.education.port.in;

import dev.tsumakov.application.profile.education.dto.outer.UserEducationPathDto;
import java.util.List;

public interface GetAllUserEducationPathsUseCase {

  List<UserEducationPathDto> execute();

}
