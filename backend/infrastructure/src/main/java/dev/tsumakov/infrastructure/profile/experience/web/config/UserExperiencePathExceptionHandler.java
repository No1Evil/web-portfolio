package dev.tsumakov.infrastructure.profile.experience.web.config;

import dev.tsumakov.application.profile.experience.exception.UserExperiencePathNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExperiencePathExceptionHandler {

  @ExceptionHandler(UserExperiencePathNotFoundException.class)
  public ResponseEntity<ProblemDetail> handleUserExperiencePathNotFound(
      UserExperiencePathNotFoundException e) {
    var detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    detail.setTitle("User Experience Path Not Found");
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
        .body(detail);
  }

}
