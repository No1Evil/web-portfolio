package dev.tsumakov.infrastructure.core.user.web.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.tsumakov.application.core.user.dto.in.AuthenticateUserDto;
import dev.tsumakov.application.core.user.dto.outer.UserDto;
import dev.tsumakov.application.core.user.exception.InvalidCredentialsException;
import dev.tsumakov.application.core.user.port.in.AuthenticateUserUseCase;
import dev.tsumakov.application.core.user.port.in.GetCurrentUserUseCase;
import dev.tsumakov.infrastructure.core.user.web.config.UserExceptionHandler;
import dev.tsumakov.infrastructure.core.user.web.dto.request.AuthenticateUserRequest;
import dev.tsumakov.infrastructure.core.user.web.dto.response.UserResponse;
import dev.tsumakov.infrastructure.core.user.web.mapper.UserWebMapper;
import dev.tsumakov.infrastructure.shared.web.config.RestExceptionHandler;
import java.time.OffsetDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

  private static final OffsetDateTime NOW = OffsetDateTime.now();

  @Mock
  private AuthenticateUserUseCase authenticateUserUseCase;
  @Mock
  private GetCurrentUserUseCase getCurrentUserUseCase;
  @Mock
  private UserWebMapper mapper;

  private MockMvc mockMvc;
  private final ObjectMapper objectMapper =
      new ObjectMapper().registerModule(new JavaTimeModule());

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders
        .standaloneSetup(new AuthController(authenticateUserUseCase, getCurrentUserUseCase, mapper))
        .setControllerAdvice(new UserExceptionHandler(), new RestExceptionHandler())
        .build();
  }

  @AfterEach
  void tearDown() {
    SecurityContextHolder.clearContext();
  }

  @Test
  void shouldLoginAndStoreSecurityContextInSession() throws Exception {
    var user = new UserDto(1, "admin", NOW, NOW);
    var expected = new UserResponse(1, "admin", NOW, NOW);

    when(mapper.toDto(any(AuthenticateUserRequest.class)))
        .thenReturn(new AuthenticateUserDto("admin", "password"));
    when(authenticateUserUseCase.execute(any())).thenReturn(user);
    when(mapper.toResponse(user)).thenReturn(expected);

    MvcResult result = mockMvc.perform(post("/api/v1/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {"username":"admin","password":"password"}
                """))
        .andExpect(status().isOk())
        .andReturn();

    var body = objectMapper.readValue(result.getResponse().getContentAsByteArray(), UserResponse.class);
    assertThat(body.id()).isEqualTo(1);
    assertThat(body.username()).isEqualTo("admin");
    assertThat(body.createdAt().toInstant()).isEqualTo(NOW.toInstant());
    assertThat(body.updatedAt().toInstant()).isEqualTo(NOW.toInstant());

    MockHttpSession session = (MockHttpSession) result.getRequest().getSession(false);
    assertThat(session).isNotNull();
    SecurityContext context = (SecurityContext) session
        .getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
    assertThat(context).isNotNull();
    assertThat(context.getAuthentication()).isNotNull();
    assertThat(context.getAuthentication().isAuthenticated()).isTrue();
    assertThat(context.getAuthentication().getAuthorities())
        .extracting(authority -> authority.getAuthority())
        .containsExactly("ROLE_ADMIN");
  }

  @Test
  void shouldReturnUnauthorizedWhenCredentialsInvalid() throws Exception {
    when(mapper.toDto(any(AuthenticateUserRequest.class)))
        .thenReturn(new AuthenticateUserDto("admin", "wrong"));
    when(authenticateUserUseCase.execute(any()))
        .thenThrow(new InvalidCredentialsException("Invalid username or password"));

    mockMvc.perform(post("/api/v1/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {"username":"admin","password":"wrong"}
                """))
        .andExpect(status().isUnauthorized())
        .andExpect(result -> {
          var node = objectMapper.readTree(result.getResponse().getContentAsByteArray());
          assertThat(node.get("title").asText()).isEqualTo("Invalid credentials");
          assertThat(node.get("status").asInt()).isEqualTo(401);
        });
  }

  @Test
  void shouldRejectBlankCredentials() throws Exception {
    mockMvc.perform(post("/api/v1/auth/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {"username":"","password":""}
                """))
        .andExpect(status().isBadRequest());

    verify(authenticateUserUseCase, never()).execute(any());
  }

  @Test
  void shouldLogoutAndInvalidateSession() throws Exception {
    MockHttpSession session = new MockHttpSession();

    MvcResult result = mockMvc.perform(post("/api/v1/auth/logout").session(session))
        .andExpect(status().isOk())
        .andReturn();

    assertThat(session.isInvalid()).isTrue();
    var cookie = result.getResponse().getCookie("JSESSIONID");
    assertThat(cookie).isNotNull();
    assertThat(cookie.getMaxAge()).isZero();
    assertThat(cookie.getPath()).isEqualTo("/");
  }
}