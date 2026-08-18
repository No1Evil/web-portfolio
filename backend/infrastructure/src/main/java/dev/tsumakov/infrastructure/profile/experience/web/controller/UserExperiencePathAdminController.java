package dev.tsumakov.infrastructure.profile.experience.web.controller;

import dev.tsumakov.application.profile.experience.port.in.CreateUserExperiencePathUseCase;
import dev.tsumakov.application.profile.experience.port.in.DeleteUserExperiencePathUseCase;
import dev.tsumakov.application.profile.experience.port.in.GetAllUserExperiencePathsUseCase;
import dev.tsumakov.application.profile.experience.port.in.GetUserExperiencePathByIdUseCase;
import dev.tsumakov.application.profile.experience.port.in.UpdateUserExperiencePathUseCase;
import dev.tsumakov.infrastructure.profile.experience.web.dto.request.CreateUserExperiencePathRequest;
import dev.tsumakov.infrastructure.profile.experience.web.dto.request.UpdateUserExperiencePathRequest;
import dev.tsumakov.infrastructure.profile.experience.web.dto.response.UserExperiencePathAdminResponse;
import dev.tsumakov.infrastructure.profile.experience.web.mapper.UserExperiencePathWebMapper;
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
@RequestMapping("api/v1/admin/profile/experience-paths")
@RequiredArgsConstructor
public class UserExperiencePathAdminController {

  private final GetAllUserExperiencePathsUseCase getAllUserExperiencePathsUseCase;
  private final GetUserExperiencePathByIdUseCase getUserExperiencePathByIdUseCase;
  private final CreateUserExperiencePathUseCase createUserExperiencePathUseCase;
  private final UpdateUserExperiencePathUseCase updateUserExperiencePathUseCase;
  private final DeleteUserExperiencePathUseCase deleteUserExperiencePathUseCase;
  private final UserExperiencePathWebMapper mapper;

  @GetMapping
  @Operation(operationId = "getAllUserExperiencePathsAdmin")
  public ResponseEntity<List<UserExperiencePathAdminResponse>> getAll() {
    var paths = getAllUserExperiencePathsUseCase.execute();
    var response = paths.stream().map(mapper::toAdminResponse).toList();
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  @Operation(operationId = "getUserExperiencePathByIdAdmin")
  public ResponseEntity<UserExperiencePathAdminResponse> getById(@PathVariable UUID id) {
    var path = getUserExperiencePathByIdUseCase.execute(id);
    return ResponseEntity.ok(mapper.toAdminResponse(path));
  }

  @PostMapping
  @Operation(operationId = "createUserExperiencePath")
  public ResponseEntity<UserExperiencePathAdminResponse> create(
      @Valid @RequestBody CreateUserExperiencePathRequest request) {
    var command = mapper.toDto(request);
    var path = createUserExperiencePathUseCase.execute(command);
    return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toAdminResponse(path));
  }

  @PatchMapping("/{id}")
  @Operation(operationId = "updateUserExperiencePath")
  public ResponseEntity<UserExperiencePathAdminResponse> update(@PathVariable UUID id,
      @Valid @RequestBody UpdateUserExperiencePathRequest request) {
    var command = mapper.toDto(id, request);
    var path = updateUserExperiencePathUseCase.execute(command);
    return ResponseEntity.ok(mapper.toAdminResponse(path));
  }

  @DeleteMapping("/{id}")
  @Operation(operationId = "deleteUserExperiencePath")
  public ResponseEntity<Void> delete(@PathVariable UUID id) {
    deleteUserExperiencePathUseCase.execute(id);
    return ResponseEntity.noContent().build();
  }
}