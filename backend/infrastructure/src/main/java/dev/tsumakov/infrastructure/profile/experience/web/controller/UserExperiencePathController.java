package dev.tsumakov.infrastructure.profile.experience.web.controller;

import dev.tsumakov.application.profile.experience.port.in.GetAllUserExperiencePathsUseCase;
import dev.tsumakov.application.profile.experience.port.in.GetUserExperiencePathByIdUseCase;
import dev.tsumakov.infrastructure.profile.experience.web.dto.response.UserExperiencePathResponse;
import dev.tsumakov.infrastructure.profile.experience.web.mapper.UserExperiencePathWebMapper;
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
@RequestMapping("api/v1/profile/experience-paths")
@RequiredArgsConstructor
public class UserExperiencePathController {

  private final GetAllUserExperiencePathsUseCase getAllUserExperiencePathsUseCase;
  private final GetUserExperiencePathByIdUseCase getUserExperiencePathByIdUseCase;
  private final UserExperiencePathWebMapper mapper;

  @GetMapping
  @Operation(operationId = "getAllUserExperiencePathsUser")
  public ResponseEntity<List<UserExperiencePathResponse>> getAll() {
    var paths = getAllUserExperiencePathsUseCase.execute();
    var response = paths.stream().map(mapper::toResponse).toList();
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  @Operation(operationId = "getUserExperiencePathByIdUser")
  public ResponseEntity<UserExperiencePathResponse> getById(@PathVariable UUID id) {
    var path = getUserExperiencePathByIdUseCase.execute(id);
    return ResponseEntity.ok(mapper.toResponse(path));
  }
}