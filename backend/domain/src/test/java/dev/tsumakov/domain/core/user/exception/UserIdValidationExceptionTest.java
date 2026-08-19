package dev.tsumakov.domain.core.user.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class UserIdValidationExceptionTest {

  @Test
  public void shouldBeRuntimeException() {
    assertThat(new UserIdValidationException("msg")).isInstanceOf(RuntimeException.class);
  }

  @Test
  public void shouldPreserveMessage() {
    var exception = new UserIdValidationException("User without id '1' is prohibited");
    assertThat(exception.getMessage()).isEqualTo("User without id '1' is prohibited");
  }

  @Test
  public void shouldAllowNullMessage() {
    assertThat(new UserIdValidationException(null).getMessage()).isNull();
  }
}
