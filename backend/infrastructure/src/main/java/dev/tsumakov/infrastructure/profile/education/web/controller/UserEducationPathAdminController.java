package dev.tsumakov.infrastructure.profile.education.web.controller;

import dev.tsumakov.application.profile.education.port.in.CreateUserEducationPathUseCase;
import dev.tsumakov.application.profile.education.port.in.DeleteUserEducationPathUseCase;
import dev.tsumakov.application.profile.education.port.in.GetAllUserEducationPathsUseCase;
import dev.tsumakov.application.profile.education.port.in.GetUserEducationPathByIdUseCase;
import dev.tsumakov.application.profile.education.port.in.UpdateUserEducationPathUseCase;
import dev.tsumakov.infrastructure.profile.education.web.dto.request.CreateUserEducationPathRequest;
import dev.tsumakov.infrastructure.profile.education.web.dto.request.UpdateUserEducationPathRequest;
import dev.tsumakov.infrastructure.profile.education.web.dto.response.UserEducationPathAdminResponse;
import dev.tsumakov.infrastructure.profile.education.web.mapper.UserEducationPathWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/profile/education-paths")
@RequiredArgsConstructor
public class UserEducationPathAdminController {

  private final GetAllUserEducationPathsUseCase getAllUserEducationPathsUseCase;
  private final GetUserEducationPathByIdUseCase getUserEducationPathByIdUseCase;
  private final CreateUserEducationPathUseCase createUserEducationPathUseCase;
  private final UpdateUserEducationPathUseCase updateUserEducationPathUseCase;
  private final DeleteUserEducationPathUseCase deleteUserEducationPathUseCase;
  private final UserEducationPathWebMapper mapper;

  @GetMapping
  @Operation(operationId = "getAllUserEducationPathsAdmin")
  public ResponseEntity<List<UserEducationPathAdminResponse>> getAll() {
    var paths = getAllUserEducationPathsUseCase.execute();
    var response = paths.stream().map(mapper::toAdminResponse).toList();
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  @Operation(operationId = "getUserEducationPathByIdAdmin")
  public ResponseEntity<UserEducationPathAdminResponse> getById(@PathVariable UUID id) {
    var path = getUserEducationPathByIdUseCase.execute(id);
    return ResponseEntity.ok(mapper.toAdminResponse(path));
  }

  @PostMapping
  @Operation(operationId = "createUserEducationPath")
  public ResponseEntity<UserEducationPathAdminResponse> create(
      @Valid @RequestBody CreateUserEducationPathRequest request) {
    var command = mapper.toDto(request);
    var path = createUserEducationPathUseCase.execute(command);
    return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toAdminResponse(path));
  }

  @PatchMapping("/{id}")
  @Operation(operationId = "updateUserEducationPath")
  public ResponseEntity<UserEducationPathAdminResponse> update(@PathVariable UUID id,
      @Valid @RequestBody UpdateUserEducationPathRequest request) {
    var command = mapper.toDto(id, request);
    var path = updateUserEducationPathUseCase.execute(command);
    return ResponseEntity.ok(mapper.toAdminResponse(path));
  }

  @DeleteMapping("/{id}")
  @Operation(operationId = "deleteUserEducationPath")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    deleteUserEducationPathUseCase.execute(id);
    return ResponseEntity.noContent().build();
  }
}