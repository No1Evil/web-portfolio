package dev.tsumakov.infrastructure.profile.summary.web.controller;

import dev.tsumakov.application.profile.summary.port.in.CreateUserSummaryUseCase;
import dev.tsumakov.application.profile.summary.port.in.GetUserSummaryUseCase;
import dev.tsumakov.application.profile.summary.port.in.UpdateUserSummaryUseCase;
import dev.tsumakov.infrastructure.profile.summary.web.dto.request.CreateUserSummaryRequest;
import dev.tsumakov.infrastructure.profile.summary.web.dto.request.UpdateUserSummaryRequest;
import dev.tsumakov.infrastructure.profile.summary.web.dto.response.UserSummaryAdminResponse;
import dev.tsumakov.infrastructure.profile.summary.web.mapper.UserSummaryWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/profile/summary")
@RequiredArgsConstructor
public class UserSummaryAdminController {

  private final GetUserSummaryUseCase getUserSummaryUseCase;
  private final UpdateUserSummaryUseCase updateUserSummaryUseCase;
  private final CreateUserSummaryUseCase createUserSummaryUseCase;
  private final UserSummaryWebMapper mapper;

  @GetMapping
  @Operation(operationId = "getUserSummaryAdmin")
  public ResponseEntity<UserSummaryAdminResponse> get() {
    var summary = getUserSummaryUseCase.execute();
    return ResponseEntity.ok(mapper.toAdminResponse(summary));
  }

  @PostMapping
  @Operation(operationId = "createUserSummary")
  public ResponseEntity<UserSummaryAdminResponse> create(
      @Valid @RequestBody CreateUserSummaryRequest request) {
    var command = mapper.toDto(request);
    var summary = createUserSummaryUseCase.execute(command);
    return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toAdminResponse(summary));
  }

  @PatchMapping
  @Operation(operationId = "updateUserSummary")
  public ResponseEntity<UserSummaryAdminResponse> update(
      @Valid @RequestBody UpdateUserSummaryRequest request) {
    var command = mapper.toDto(request);
    var summary = updateUserSummaryUseCase.execute(command);
    return ResponseEntity.ok(mapper.toAdminResponse(summary));
  }
}