package dev.tsumakov.infrastructure.profile.education.web.config;

import dev.tsumakov.application.profile.education.exception.UserEducationPathNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserEducationPathExceptionHandler {

  @ExceptionHandler(UserEducationPathNotFoundException.class)
  public ResponseEntity<ProblemDetail> handleUserEducationPathNotFound(
      UserEducationPathNotFoundException e) {
    var detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    detail.setTitle("User Education Path Not Found");
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
        .body(detail);
  }
}
