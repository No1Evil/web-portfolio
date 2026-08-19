package dev.tsumakov.infrastructure.core.user.web.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.tsumakov.application.core.user.dto.in.UpdateUserPasswordDto;
import dev.tsumakov.application.core.user.dto.outer.UserDto;
import dev.tsumakov.application.core.user.exception.UserNotFoundException;
import dev.tsumakov.application.core.user.port.in.UpdateUserPasswordUseCase;
import dev.tsumakov.infrastructure.core.user.web.config.UserExceptionHandler;
import dev.tsumakov.infrastructure.core.user.web.dto.request.UpdateUserPasswordRequest;
import dev.tsumakov.infrastructure.core.user.web.dto.response.UserResponse;
import dev.tsumakov.infrastructure.core.user.web.mapper.UserWebMapper;
import dev.tsumakov.infrastructure.shared.web.config.RestExceptionHandler;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

  private static final OffsetDateTime NOW = OffsetDateTime.now();

  @Mock
  private UpdateUserPasswordUseCase updateUserPasswordUseCase;
  @Mock
  private UserWebMapper mapper;

  private MockMvc mockMvc;
  private final ObjectMapper objectMapper =
      new ObjectMapper().registerModule(new JavaTimeModule());

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders
        .standaloneSetup(new UserController(updateUserPasswordUseCase, mapper))
        .setControllerAdvice(new UserExceptionHandler(), new RestExceptionHandler())
        .build();
  }

  @Test
  void shouldUpdatePasswordAndReturnUpdatedUser() throws Exception {
    var command = new UpdateUserPasswordDto(1, "oldPass", "newPass123");
    var user = new UserDto(1, "admin", NOW, NOW);
    var expected = new UserResponse(1, "admin", NOW, NOW);

    when(mapper.toDto(eq(1), any(UpdateUserPasswordRequest.class))).thenReturn(command);
    when(updateUserPasswordUseCase.execute(command)).thenReturn(user);
    when(mapper.toResponse(user)).thenReturn(expected);

    MvcResult result = mockMvc.perform(patch("/api/v1/admin/user/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {"oldRawPassword":"oldPass","rawPassword":"newPass123"}
                """))
        .andExpect(status().isOk())
        .andReturn();

    var body = objectMapper.readValue(result.getResponse().getContentAsByteArray(), UserResponse.class);
    assertThat(body.id()).isEqualTo(1);
    assertThat(body.username()).isEqualTo("admin");
    assertThat(body.createdAt().toInstant()).isEqualTo(NOW.toInstant());
    assertThat(body.updatedAt().toInstant()).isEqualTo(NOW.toInstant());
    verify(mapper).toDto(eq(1), any(UpdateUserPasswordRequest.class));
  }

  @Test
  void shouldRejectBlankOldPassword() throws Exception {
    mockMvc.perform(patch("/api/v1/admin/user/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {"oldRawPassword":"","rawPassword":"newPass123"}
                """))
        .andExpect(status().isBadRequest());

    verify(updateUserPasswordUseCase, never()).execute(any());
  }

  @Test
  void shouldRejectShortNewPassword() throws Exception {
    mockMvc.perform(patch("/api/v1/admin/user/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {"oldRawPassword":"oldPass","rawPassword":"short"}
                """))
        .andExpect(status().isBadRequest());

    verify(updateUserPasswordUseCase, never()).execute(any());
  }

  @Test
  void shouldReturnNotFoundWhenUserMissing() throws Exception {
    when(mapper.toDto(eq(1), any(UpdateUserPasswordRequest.class)))
        .thenReturn(new UpdateUserPasswordDto(1, "oldPass", "newPass123"));
    when(updateUserPasswordUseCase.execute(any()))
        .thenThrow(new UserNotFoundException("User with id 1 not found"));

    mockMvc.perform(patch("/api/v1/admin/user/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {"oldRawPassword":"oldPass","rawPassword":"newPass123"}
                """))
        .andExpect(status().isNotFound())
        .andExpect(result -> {
          var node = objectMapper.readTree(result.getResponse().getContentAsByteArray());
          assertThat(node.get("title").asText()).isEqualTo("User Not Found");
          assertThat(node.get("status").asInt()).isEqualTo(404);
        });
  }
}