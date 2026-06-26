package dev.tsumakov.domain.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class DomainValidationExceptionTest {

  @Test
  public void shouldPreserveMessage() {
    var exception = new DomainValidationException("test message");
    assertThat(exception.getMessage()).isEqualTo("test message");
  }

  @Test
  public void shouldBeRuntimeException() {
    var exception = new DomainValidationException("msg");
    assertThat(exception).isInstanceOf(RuntimeException.class);
  }
}
