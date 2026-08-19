package dev.tsumakov.infrastructure.profile.summary.web.config;

import dev.tsumakov.application.profile.summary.exception.UserSummaryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserSummaryExceptionHandler {

  @ExceptionHandler(UserSummaryNotFoundException.class)
  public ResponseEntity<ProblemDetail> handleUserSummaryNotFound(UserSummaryNotFoundException e) {
    var detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    detail.setTitle("User Summary Not Found");
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
        .body(detail);
  }

}