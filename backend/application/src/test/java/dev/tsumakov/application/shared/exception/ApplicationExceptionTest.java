package dev.tsumakov.application.shared.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ApplicationExceptionTest {

  @Test
  void shouldPreserveMessage() {
    var exception = new ApplicationException("test message");
    assertThat(exception.getMessage()).isEqualTo("test message");
  }

  @Test
  void shouldBeRuntimeException() {
    var exception = new ApplicationException("msg");
    assertThat(exception).isInstanceOf(RuntimeException.class);
  }
}
