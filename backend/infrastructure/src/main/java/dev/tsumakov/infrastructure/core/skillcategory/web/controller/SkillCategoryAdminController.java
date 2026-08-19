package dev.tsumakov.infrastructure.core.skillcategory.web.controller;

import dev.tsumakov.application.core.skillcategory.port.in.CreateSkillCategoryUseCase;
import dev.tsumakov.application.core.skillcategory.port.in.DeleteSkillCategoryUseCase;
import dev.tsumakov.application.core.skillcategory.port.in.GetAllSkillCategoriesUseCase;
import dev.tsumakov.application.core.skillcategory.port.in.GetSkillCategoryByIdUseCase;
import dev.tsumakov.application.core.skillcategory.port.in.UpdateSkillCategoryUseCase;
import dev.tsumakov.infrastructure.core.skillcategory.web.dto.request.CreateSkillCategoryRequest;
import dev.tsumakov.infrastructure.core.skillcategory.web.dto.request.UpdateSkillCategoryRequest;
import dev.tsumakov.infrastructure.core.skillcategory.web.dto.response.SkillCategoryAdminResponse;
import dev.tsumakov.infrastructure.core.skillcategory.web.mapper.SkillCategoryWebMapper;
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
@RequestMapping("api/v1/admin/skill-categories")
@RequiredArgsConstructor
public class SkillCategoryAdminController {

  private final GetAllSkillCategoriesUseCase getAllSkillCategoriesUseCase;
  private final GetSkillCategoryByIdUseCase getSkillCategoryByIdUseCase;
  private final CreateSkillCategoryUseCase createSkillCategoryUseCase;
  private final UpdateSkillCategoryUseCase updateSkillCategoryUseCase;
  private final DeleteSkillCategoryUseCase deleteSkillCategoryUseCase;
  private final SkillCategoryWebMapper mapper;

  @GetMapping
  @Operation(operationId = "getAllSkillCategoriesAdmin")
  public ResponseEntity<List<SkillCategoryAdminResponse>> getAll() {
    var categories = getAllSkillCategoriesUseCase.execute();
    var response = categories.stream().map(mapper::toAdminResponse).toList();
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  @Operation(operationId = "getSkillCategoryByIdAdmin")
  public ResponseEntity<SkillCategoryAdminResponse> getById(@PathVariable Integer id) {
    var category = getSkillCategoryByIdUseCase.execute(id);
    return ResponseEntity.ok(mapper.toAdminResponse(category));
  }

  @PostMapping
  @Operation(operationId = "createSkillCategory")
  public ResponseEntity<SkillCategoryAdminResponse> create(
      @Valid @RequestBody CreateSkillCategoryRequest request) {
    var command = mapper.toDto(request);
    var category = createSkillCategoryUseCase.execute(command);
    return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toAdminResponse(category));
  }

  @PatchMapping("/{id}")
  @Operation(operationId = "updateSkillCategory")
  public ResponseEntity<SkillCategoryAdminResponse> update(@PathVariable Integer id,
      @Valid @RequestBody UpdateSkillCategoryRequest request) {
    var command = mapper.toDto(id, request);
    var category = updateSkillCategoryUseCase.execute(command);
    return ResponseEntity.ok(mapper.toAdminResponse(category));
  }

  @DeleteMapping("/{id}")
  @Operation(operationId = "deleteSkillCategory")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    deleteSkillCategoryUseCase.execute(id);
    return ResponseEntity.noContent().build();
  }
}
