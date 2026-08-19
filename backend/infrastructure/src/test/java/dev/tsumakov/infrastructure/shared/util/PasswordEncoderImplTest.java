package dev.tsumakov.infrastructure.shared.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class PasswordEncoderImplTest {

  @Mock
  private PasswordEncoder passwordEncoder;

  private PasswordEncoderImpl encoderImpl;

  @BeforeEach
  void setUp() {
    encoderImpl = new PasswordEncoderImpl(passwordEncoder);
  }

  @Test
  void shouldDelegateEncode() {
    when(passwordEncoder.encode("raw")).thenReturn("encoded");

    var result = encoderImpl.encodePassword("raw");

    assertThat(result).isEqualTo("encoded");
    verify(passwordEncoder).encode("raw");
  }

  @Test
  void shouldDelegateMatches() {
    when(passwordEncoder.matches("raw", "encoded")).thenReturn(true);

    var result = encoderImpl.matches("raw", "encoded");

    assertThat(result).isTrue();
    verify(passwordEncoder).matches("raw", "encoded");
  }
}