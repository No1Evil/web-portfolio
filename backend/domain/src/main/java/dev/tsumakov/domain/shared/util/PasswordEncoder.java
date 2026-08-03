package dev.tsumakov.domain.shared.util;

public interface PasswordEncoder {

  String encodePassword(String rawPassword);

}
