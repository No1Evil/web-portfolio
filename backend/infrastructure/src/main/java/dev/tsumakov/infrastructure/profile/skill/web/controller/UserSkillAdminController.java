package dev.tsumakov.infrastructure.profile.skill.web.controller;

import dev.tsumakov.application.profile.skill.dto.in.AssignSkillToUserDto;
import dev.tsumakov.application.profile.skill.dto.in.UnassignSkillFromUserDto;
import dev.tsumakov.application.profile.skill.port.in.AssignSkillToUserUseCase;
import dev.tsumakov.application.profile.skill.port.in.GetAllUserSkillsUseCase;
import dev.tsumakov.application.profile.skill.port.in.UnassignSkillFromUserUseCase;
import dev.tsumakov.infrastructure.profile.skill.web.dto.request.AssignSkillToUserRequest;
import dev.tsumakov.infrastructure.profile.skill.web.dto.request.UnassignSkillFromUserRequest;
import dev.tsumakov.infrastructure.profile.skill.web.dto.response.UserSkillResponse;
import dev.tsumakov.infrastructure.profile.skill.web.mapper.UserSkillWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/profile/skills")
@RequiredArgsConstructor
public class UserSkillAdminController {

  private final GetAllUserSkillsUseCase getAllUserSkillsUseCase;
  private final AssignSkillToUserUseCase assignSkillToUserUseCase;
  private final UnassignSkillFromUserUseCase unassignSkillFromUserUseCase;
  private final UserSkillWebMapper mapper;

  @GetMapping
  @Operation(operationId = "getAllUserSkillsAdmin")
  public ResponseEntity<List<UserSkillResponse>> getAll() {
    var skills = getAllUserSkillsUseCase.execute();
    var response = skills.stream().map(mapper::toResponse).toList();
    return ResponseEntity.ok(response);
  }

  @PostMapping
  @Operation(operationId = "assignSkillToUser")
  public ResponseEntity<Void> assign(@Valid @RequestBody AssignSkillToUserRequest request) {
    assignSkillToUserUseCase.execute(new AssignSkillToUserDto(request.userId(), request.skillId()));
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping
  @Operation(operationId = "unassignSkillFromUser")
  public ResponseEntity<Void> unassign(@Valid @RequestBody UnassignSkillFromUserRequest request) {
    unassignSkillFromUserUseCase.execute(new UnassignSkillFromUserDto(request.userId(), request.skillId()));
    return ResponseEntity.noContent().build();
  }
}