package dev.tsumakov.infrastructure.shared.web.config;

import dev.tsumakov.application.core.user.exception.PasswordValidationException;
import dev.tsumakov.application.shared.exception.ApplicationException;
import dev.tsumakov.domain.core.user.exception.UserIdValidationException;
import dev.tsumakov.domain.shared.exception.DomainValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler({DomainValidationException.class, UserIdValidationException.class})
  public ResponseEntity<ProblemDetail> handleValidationFailed(RuntimeException e) {
    var detail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    detail.setTitle("Validation Failed");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
        .body(detail);
  }

  @ExceptionHandler(PasswordValidationException.class)
  public ResponseEntity<ProblemDetail> handlePasswordValidationFailed(PasswordValidationException e) {
    var detail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    detail.setTitle("Password Validation Failed");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
        .body(detail);
  }

  @ExceptionHandler(ApplicationException.class)
  public ResponseEntity<ProblemDetail> handleApplicationError(ApplicationException e) {
    var detail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    detail.setTitle("Application Error");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
        .body(detail);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ProblemDetail> handleInternalError(Exception e) {
    log.atError().log(e.getMessage());
    var detail =
        ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,
            "An unexpected error occurred");
    detail.setTitle("Internal Server Error");
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .contentType(MediaType.APPLICATION_PROBLEM_JSON)
        .body(detail);
  }

}