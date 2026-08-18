package dev.tsumakov.infrastructure.core.skill.web.config;

import dev.tsumakov.application.core.skill.exception.SkillNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SkillExceptionHandler {

  @ExceptionHandler(SkillNotFoundException.class)
  public ResponseEntity<ProblemDetail> handleSkillNotFound(SkillNotFoundException e) {
    var detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    detail.setTitle("Skill Not Found");
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
        .body(detail);
  }

}
