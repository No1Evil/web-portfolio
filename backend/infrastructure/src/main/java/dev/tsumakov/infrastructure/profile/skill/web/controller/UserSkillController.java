package dev.tsumakov.infrastructure.profile.skill.web.controller;

import dev.tsumakov.application.profile.skill.port.in.GetAllUserSkillsUseCase;
import dev.tsumakov.infrastructure.profile.skill.web.dto.response.UserSkillResponse;
import dev.tsumakov.infrastructure.profile.skill.web.mapper.UserSkillWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/profile/skills")
@RequiredArgsConstructor
public class UserSkillController {

  private final GetAllUserSkillsUseCase getAllUserSkillsUseCase;
  private final UserSkillWebMapper mapper;

  @GetMapping
  @Operation(operationId = "getAllUserSkillsUser")
  public ResponseEntity<List<UserSkillResponse>> getAll() {
    var skills = getAllUserSkillsUseCase.execute();
    var response = skills.stream().map(mapper::toResponse).toList();
    return ResponseEntity.ok(response);
  }
}