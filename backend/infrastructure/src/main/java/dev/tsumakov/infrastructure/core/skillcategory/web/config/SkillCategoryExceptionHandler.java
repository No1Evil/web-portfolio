package dev.tsumakov.infrastructure.core.skillcategory.web.config;

import dev.tsumakov.application.core.skillcategory.exception.SkillCategoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SkillCategoryExceptionHandler {

  @ExceptionHandler(SkillCategoryNotFoundException.class)
  public ResponseEntity<ProblemDetail> handleSkillCategoryNotFound(
      SkillCategoryNotFoundException e) {
    var detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    detail.setTitle("Skill Category Not Found");
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
        .body(detail);
  }

}
