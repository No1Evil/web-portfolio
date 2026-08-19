package dev.tsumakov.infrastructure.profile.education.web.controller;

import dev.tsumakov.application.profile.education.port.in.GetAllUserEducationPathsUseCase;
import dev.tsumakov.application.profile.education.port.in.GetUserEducationPathByIdUseCase;
import dev.tsumakov.infrastructure.profile.education.web.dto.response.UserEducationPathResponse;
import dev.tsumakov.infrastructure.profile.education.web.mapper.UserEducationPathWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/profile/education-paths")
@RequiredArgsConstructor
public class UserEducationPathController {

  private final GetAllUserEducationPathsUseCase getAllUserEducationPathsUseCase;
  private final GetUserEducationPathByIdUseCase getUserEducationPathByIdUseCase;
  private final UserEducationPathWebMapper mapper;

  @GetMapping
  @Operation(operationId = "getAllUserEducationPathsUser")
  public ResponseEntity<List<UserEducationPathResponse>> getAll() {
    var paths = getAllUserEducationPathsUseCase.execute();
    var response = paths.stream().map(mapper::toResponse).toList();
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  @Operation(operationId = "getUserEducationPathByIdUser")
  public ResponseEntity<UserEducationPathResponse> getById(@PathVariable UUID id) {
    var path = getUserEducationPathByIdUseCase.execute(id);
    return ResponseEntity.ok(mapper.toResponse(path));
  }
}