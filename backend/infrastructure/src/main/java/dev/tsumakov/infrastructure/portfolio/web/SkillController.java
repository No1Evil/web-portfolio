package dev.tsumakov.infrastructure.portfolio.web;

import dev.tsumakov.application.portfolio.port.in.skill.CreateSkillUseCase;
import dev.tsumakov.application.portfolio.port.in.skill.DeleteSkillUseCase;
import dev.tsumakov.application.portfolio.port.in.skill.GetAllSkillsUseCase;
import dev.tsumakov.application.portfolio.port.in.skill.GetSkillByIdUseCase;
import dev.tsumakov.application.portfolio.port.in.skill.GetSkillsByCategoryUseCase;
import dev.tsumakov.infrastructure.portfolio.web.dto.skill.CreateSkillRequest;
import dev.tsumakov.infrastructure.portfolio.web.dto.skill.SkillResponse;
import dev.tsumakov.infrastructure.portfolio.web.mapper.SkillWebMapper;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/skills")
@RequiredArgsConstructor
public class SkillController {

  private final SkillWebMapper mapper;

  private final CreateSkillUseCase createSkillUseCase;
  private final DeleteSkillUseCase deleteSkillUseCase;
  private final GetAllSkillsUseCase getAllSkillsUseCase;
  private final GetSkillByIdUseCase getSkillByIdUseCase;
  private final GetSkillsByCategoryUseCase getSkillsByCategoryUseCase;


  @GetMapping("/{id}")
  public ResponseEntity<SkillResponse> getById(@PathVariable Integer id) {
    var execute = getSkillByIdUseCase.execute(id);
    var response = mapper.toResponse(execute);
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<SkillResponse> createSkill(@Valid @RequestBody CreateSkillRequest request) {
    var execute = createSkillUseCase.execute(mapper.toDto(request));
    var response = mapper.toResponse(execute);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteSkill(@PathVariable Integer id) {
    deleteSkillUseCase.execute(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping
  public ResponseEntity<List<SkillResponse>> getAllSkills() {
    var execute = getAllSkillsUseCase.execute();
    var response = execute.stream().map(mapper::toResponse).toList();
    return ResponseEntity.ok(response);
  }

  @GetMapping("/by-category/{id}")
  public ResponseEntity<List<SkillResponse>> getSkillsByCategoryId(@PathVariable Integer id) {
    var execute = getSkillsByCategoryUseCase.execute(id);
    var response = execute.stream().map(mapper::toResponse).toList();
    return ResponseEntity.ok(response);
  }
}
