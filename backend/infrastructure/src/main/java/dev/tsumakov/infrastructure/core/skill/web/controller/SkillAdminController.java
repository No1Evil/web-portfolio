package dev.tsumakov.infrastructure.core.skill.web.controller;

import dev.tsumakov.application.core.skill.port.in.CreateSkillUseCase;
import dev.tsumakov.application.core.skill.port.in.DeleteSkillUseCase;
import dev.tsumakov.application.core.skill.port.in.GetAllSkillsUseCase;
import dev.tsumakov.application.core.skill.port.in.GetSkillByIdUseCase;
import dev.tsumakov.application.core.skill.port.in.UpdateSkillUseCase;
import dev.tsumakov.infrastructure.core.skill.web.dto.request.CreateSkillRequest;
import dev.tsumakov.infrastructure.core.skill.web.dto.request.UpdateSkillRequest;
import dev.tsumakov.infrastructure.core.skill.web.dto.response.SkillAdminResponse;
import dev.tsumakov.infrastructure.core.skill.web.mapper.SkillWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
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
@RequestMapping("api/v1/admin/skills")
@RequiredArgsConstructor
public class SkillAdminController {

  private final GetAllSkillsUseCase getAllSkillsUseCase;
  private final GetSkillByIdUseCase getSkillByIdUseCase;
  private final DeleteSkillUseCase deleteSkillUseCase;
  private final CreateSkillUseCase createSkillUseCase;
  private final UpdateSkillUseCase updateSkillUseCase;
  private final SkillWebMapper mapper;

  @GetMapping
  @Operation(operationId = "getAllSkillsAdmin")
  public ResponseEntity<List<SkillAdminResponse>> getAll() {
    var skills = getAllSkillsUseCase.execute();
    var response = skills.stream().map(mapper::toAdminResponse).toList();
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  @Operation(operationId = "getSkillByIdAdmin")
  public ResponseEntity<SkillAdminResponse> getById(@PathVariable Integer id) {
    var skill = getSkillByIdUseCase.execute(id);
    var response = mapper.toAdminResponse(skill);
    return ResponseEntity.ok(response);
  }

  @PostMapping
  @Operation(operationId = "createSkill")
  public ResponseEntity<SkillAdminResponse> create(@Valid @RequestBody CreateSkillRequest request) {
    var command = mapper.toDto(request);
    var skill = createSkillUseCase.execute(command);
    var response = mapper.toAdminResponse(skill);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PatchMapping("/{id}")
  @Operation(operationId = "updateSkill")
  public ResponseEntity<SkillAdminResponse> update(
      @PathVariable Integer id, @Valid @RequestBody UpdateSkillRequest request
  ) {
    var command = mapper.toDto(id, request);
    var skill = updateSkillUseCase.execute(command);
    var response = mapper.toAdminResponse(skill);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  @Operation(operationId = "deleteSkill")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    deleteSkillUseCase.execute(id);
    return ResponseEntity.noContent().build();
  }

}
