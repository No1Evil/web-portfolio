package dev.tsumakov.infrastructure.core.user.bootstrap;

import dev.tsumakov.domain.core.user.model.User;
import dev.tsumakov.domain.core.user.repository.UserRepository;
import dev.tsumakov.domain.shared.util.PasswordEncoder;
import java.time.OffsetDateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AdminUserInitializer implements ApplicationRunner {

  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final String username;
  private final String rawPassword;

  public AdminUserInitializer(
      UserRepository repository,
      PasswordEncoder passwordEncoder,
      @Value("${app.admin.username}") String username,
      @Value("${app.admin.password}") String rawPassword) {
    this.repository = repository;
    this.passwordEncoder = passwordEncoder;
    this.username = username;
    this.rawPassword = rawPassword;
  }

  @Override
  public void run(ApplicationArguments args) {
    requireNotBlank(username, "ADMIN_USERNAME must not be blank");
    requireNotBlank(rawPassword, "ADMIN_PASSWORD must not be blank");

    repository.findById(1).ifPresentOrElse(this::updateIfNeeded, this::create);
  }

  private void updateIfNeeded(User existing) {
    if (username.equals(existing.username())
        && passwordEncoder.matches(rawPassword, existing.passwordHash())) {
      return;
    }
    String hash = passwordEncoder.encodePassword(rawPassword);
    repository.update(existing.updateCredentials(username, hash));
  }

  private void create() {
    String hash = passwordEncoder.encodePassword(rawPassword);
    repository.create(new User(1, username, hash, OffsetDateTime.now(), OffsetDateTime.now(), 0L));
  }

  private static void requireNotBlank(String value, String message) {
    if (value == null || value.isBlank()) {
      throw new IllegalStateException(message);
    }
  }
}
