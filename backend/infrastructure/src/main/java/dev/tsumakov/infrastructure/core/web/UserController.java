package dev.tsumakov.infrastructure.core.web;

import dev.tsumakov.application.core.dto.api.UserDto;
import dev.tsumakov.application.core.dto.in.CreateUserDto;
import dev.tsumakov.application.core.port.in.user.CreateUserUseCase;
import dev.tsumakov.application.core.port.in.user.GetAllUsersUseCase;
import dev.tsumakov.application.core.port.in.user.GetUserByEmailUseCase;
import dev.tsumakov.infrastructure.core.web.dto.user.CreateUserRequest;
import dev.tsumakov.infrastructure.core.web.dto.user.UserCreatedResponse;
import dev.tsumakov.infrastructure.core.web.dto.user.UserResponse;
import dev.tsumakov.infrastructure.core.web.mapper.UserWebMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

  private final CreateUserUseCase createUserUseCase;
  private final GetAllUsersUseCase getAllUsersUseCase;
  private final GetUserByEmailUseCase getUserByEmailUseCase;
  private final UserWebMapper userWebMapper;

  @PostMapping
  public ResponseEntity<UserCreatedResponse> create(@Valid @RequestBody CreateUserRequest request) {
    CreateUserDto dto = userWebMapper.toDto(request);
    UserDto createdUser = createUserUseCase.execute(dto);
    UserCreatedResponse response = userWebMapper.toCreatedResponse(createdUser);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping
  public ResponseEntity<List<UserResponse>> get() {
    List<UserDto> users = getAllUsersUseCase.execute();
    var response = userWebMapper.toCreatedResponse(users);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{email}")
  public ResponseEntity<UserResponse> get(@Valid @PathVariable @Email String email) {
    UserDto user = getUserByEmailUseCase.execute(email);
    UserResponse response = userWebMapper.toResponse(user);
    return ResponseEntity.status(HttpStatus.FOUND).body(response);
  }
}
