package dev.tsumakov.infrastructure.profile.summary.web.controller;

import dev.tsumakov.application.profile.summary.port.in.GetUserSummaryUseCase;
import dev.tsumakov.infrastructure.profile.summary.web.dto.response.UserSummaryResponse;
import dev.tsumakov.infrastructure.profile.summary.web.mapper.UserSummaryWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/profile/summary")
@RequiredArgsConstructor
public class UserSummaryController {

  private final GetUserSummaryUseCase getUserSummaryUseCase;
  private final UserSummaryWebMapper mapper;

  @GetMapping
  @Operation(operationId = "getUserSummaryUser")
  public ResponseEntity<UserSummaryResponse> get() {
    var summary = getUserSummaryUseCase.execute();
    return ResponseEntity.ok(mapper.toResponse(summary));
  }
}