package dev.tsumakov.infrastructure.core.skillcategory.web.controller;

import dev.tsumakov.application.core.skillcategory.port.in.GetAllSkillCategoriesUseCase;
import dev.tsumakov.application.core.skillcategory.port.in.GetSkillCategoryByIdUseCase;
import dev.tsumakov.infrastructure.core.skillcategory.web.dto.response.SkillCategoryUserResponse;
import dev.tsumakov.infrastructure.core.skillcategory.web.mapper.SkillCategoryWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/skill-categories")
@RequiredArgsConstructor
public class SkillCategoryUserController {

  private final GetAllSkillCategoriesUseCase getAllSkillCategoriesUseCase;
  private final GetSkillCategoryByIdUseCase getSkillCategoryByIdUseCase;
  private final SkillCategoryWebMapper mapper;

  @GetMapping
  @Operation(operationId = "getAllSkillCategoriesUser")
  public ResponseEntity<List<SkillCategoryUserResponse>> getAll() {
    var categories = getAllSkillCategoriesUseCase.execute();
    var response = categories.stream().map(mapper::toUserResponse).toList();
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  @Operation(operationId = "getSkillCategoryByIdUser")
  public ResponseEntity<SkillCategoryUserResponse> getById(@PathVariable Integer id) {
    var category = getSkillCategoryByIdUseCase.execute(id);
    return ResponseEntity.ok(mapper.toUserResponse(category));
  }
}
