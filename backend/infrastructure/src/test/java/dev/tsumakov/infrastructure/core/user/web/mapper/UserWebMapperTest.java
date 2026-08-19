package dev.tsumakov.infrastructure.core.user.web.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import dev.tsumakov.application.core.user.dto.outer.UserDto;
import dev.tsumakov.infrastructure.core.user.web.dto.request.AuthenticateUserRequest;
import dev.tsumakov.infrastructure.core.user.web.dto.request.UpdateUserPasswordRequest;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserWebMapperTest {

  private static final OffsetDateTime NOW = OffsetDateTime.now();

  private UserWebMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = new UserWebMapperImpl();
  }

  @Test
  void shouldMapUpdateUserPasswordRequestWithUserId() {
    var request = new UpdateUserPasswordRequest("oldPass", "newPass123");

    var dto = mapper.toDto(1, request);

    assertThat(dto.userId()).isEqualTo(1);
    assertThat(dto.oldRawPassword()).isEqualTo("oldPass");
    assertThat(dto.rawPassword()).isEqualTo("newPass123");
  }

  @Test
  void shouldMapAuthenticateUserRequest() {
    var request = new AuthenticateUserRequest("admin", "password");

    var dto = mapper.toDto(request);

    assertThat(dto.username()).isEqualTo("admin");
    assertThat(dto.password()).isEqualTo("password");
  }

  @Test
  void shouldMapUserDtoToResponse() {
    var user = new UserDto(1, "admin", NOW, NOW);

    var response = mapper.toResponse(user);

    assertThat(response.id()).isEqualTo(1);
    assertThat(response.username()).isEqualTo("admin");
    assertThat(response.createdAt()).isEqualTo(NOW);
    assertThat(response.updatedAt()).isEqualTo(NOW);
  }
}