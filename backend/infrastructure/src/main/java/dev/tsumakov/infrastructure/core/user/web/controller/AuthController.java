package dev.tsumakov.infrastructure.core.user.web.controller;

import dev.tsumakov.application.core.user.port.in.AuthenticateUserUseCase;
import dev.tsumakov.infrastructure.core.user.web.dto.request.AuthenticateUserRequest;
import dev.tsumakov.infrastructure.core.user.web.dto.response.UserResponse;
import dev.tsumakov.infrastructure.core.user.web.mapper.UserWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthenticateUserUseCase authenticateUserUseCase;
  private final UserWebMapper mapper;

  @PostMapping("/login")
  @Operation(operationId = "login")
  public ResponseEntity<UserResponse> login(@Valid @RequestBody AuthenticateUserRequest request,
      HttpServletRequest httpRequest) {
    var command = mapper.toDto(request);
    var user = authenticateUserUseCase.execute(command);

    var authorities = List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
    var authentication = new UsernamePasswordAuthenticationToken(user.username(), null, authorities);

    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
    securityContext.setAuthentication(authentication);
    SecurityContextHolder.setContext(securityContext);

    HttpSession session = httpRequest.getSession(true);
    httpRequest.changeSessionId();
    session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);

    var response = mapper.toResponse(user);

    return ResponseEntity.ok(response);
  }

  @PostMapping("/logout")
  @Operation(operationId = "logout")
  public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
    HttpSession session = request.getSession(false);
    if (session != null) {
      session.invalidate();
    }

    SecurityContextHolder.clearContext();

    Cookie cookie = new Cookie("JSESSIONID", null);
    cookie.setPath("/");
    cookie.setHttpOnly(true);
    cookie.setMaxAge(0);
    response.addCookie(cookie);

    return ResponseEntity.ok().build();
  }

}
