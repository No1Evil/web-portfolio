package dev.tsumakov.infrastructure.core.skill.web.controller;

import dev.tsumakov.application.core.skill.port.in.GetAllSkillsUseCase;
import dev.tsumakov.application.core.skill.port.in.GetSkillByIdUseCase;
import dev.tsumakov.infrastructure.core.skill.web.dto.response.SkillUserResponse;
import dev.tsumakov.infrastructure.core.skill.web.mapper.SkillWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/skills")
@RequiredArgsConstructor
public class SkillUserController {

  private final GetAllSkillsUseCase getAllSkillsUseCase;
  private final GetSkillByIdUseCase getSkillByIdUseCase;
  private final SkillWebMapper mapper;

@GetMapping()
  @Operation(operationId = "getAllSkillsUser")
  public ResponseEntity<List<SkillUserResponse>> getAll() {
    var skills = getAllSkillsUseCase.execute();
    var response = skills.stream().map(mapper::toUserResponse).toList();
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  @Operation(operationId = "getSkillByIdUser")
  public ResponseEntity<SkillUserResponse> getById(@PathVariable Integer id) {
    var skill = getSkillByIdUseCase.execute(id);
    var response = mapper.toUserResponse(skill);
    return ResponseEntity.ok(response);
  }
}
