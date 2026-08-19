package dev.tsumakov.infrastructure.core.user.web.controller;

import dev.tsumakov.application.core.user.port.in.UpdateUserPasswordUseCase;
import dev.tsumakov.infrastructure.core.user.web.dto.request.UpdateUserPasswordRequest;
import dev.tsumakov.infrastructure.core.user.web.dto.response.UserResponse;
import dev.tsumakov.infrastructure.core.user.web.mapper.UserWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/user")
@RequiredArgsConstructor
public class UserController {

  private final UpdateUserPasswordUseCase updateUserPasswordUseCase;
  private final UserWebMapper mapper;

  @PatchMapping("/{id}")
  @Operation(operationId = "updateUserPassword")
  public ResponseEntity<UserResponse> updatePassword(@PathVariable Integer id, @Valid @RequestBody
      UpdateUserPasswordRequest request) {
    var command = mapper.toDto(id, request);
    var updatedUser = updateUserPasswordUseCase.execute(command);
    var response = mapper.toResponse(updatedUser);
    return ResponseEntity.ok(response);
  }

}
