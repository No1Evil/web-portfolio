package dev.tsumakov.application.profile.summary.exception;

public class UserSummaryNotFoundException extends RuntimeException {

  public UserSummaryNotFoundException(String message) {
    super(message);
  }
}
