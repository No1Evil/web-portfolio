package dev.tsumakov.application.shared.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class ApplicationExceptionTest {

  @Test
  void shouldBeARuntimeException() {
    assertThat(new ApplicationException("boom"))
        .isInstanceOf(RuntimeException.class);
  }

  @Test
  void shouldPreserveMessage() {
    assertThat(new ApplicationException("boom")).hasMessage("boom");
  }

  @Test
  void shouldAcceptNullMessage() {
    assertThat(new ApplicationException(null)).hasMessage(null);
  }

  @Test
  void shouldBeThrownAsIs() {
    assertThatThrownBy(() -> {
      throw new ApplicationException("boom");
    }).isInstanceOf(ApplicationException.class)
        .hasMessage("boom");
  }
}