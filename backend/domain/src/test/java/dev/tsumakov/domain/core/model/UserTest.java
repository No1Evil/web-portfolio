package dev.tsumakov.domain.core.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import dev.tsumakov.domain.shared.exception.DomainValidationException;
import dev.tsumakov.domain.portfolio.model.Skill;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class UserTest {

  private static final UUID VALID_UUID = UUID.randomUUID();
  private static final OffsetDateTime VALID_TIME = OffsetDateTime.now();
  private static final HashSet<Role> VALID_ROLE_SET = new HashSet<>();
  private static final HashSet<Skill> VALID_SKILL_SET = new HashSet<>();

  @Test
  public void shouldCreateUserWhenAllFieldsAreValid() {
    assertThatCode(
        () -> new User(VALID_UUID, "test", "test", "test", "url", "pass", VALID_ROLE_SET,
            VALID_SKILL_SET, VALID_TIME, VALID_TIME))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldThrowExceptionWhenIdIsNull() {
    assertThatThrownBy(
        () -> new User(null, "test", "test", "test", "url", "pass", VALID_ROLE_SET, VALID_SKILL_SET,
            VALID_TIME, VALID_TIME))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenFirstNameIsBlank() {
    assertThatThrownBy(
        () -> new User(VALID_UUID, null, "test", "test", "url", "pass", VALID_ROLE_SET,
            VALID_SKILL_SET, VALID_TIME, VALID_TIME))
        .isInstanceOf(DomainValidationException.class);
    assertThatThrownBy(
        () -> new User(VALID_UUID, "  ", "test", "test", "url", "pass", VALID_ROLE_SET,
            VALID_SKILL_SET, VALID_TIME, VALID_TIME))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenSecondNameIsBlank() {
    assertThatThrownBy(
        () -> new User(VALID_UUID, "test", null, "test", "url", "pass", VALID_ROLE_SET,
            VALID_SKILL_SET, VALID_TIME, VALID_TIME))
        .isInstanceOf(DomainValidationException.class);
    assertThatThrownBy(
        () -> new User(VALID_UUID, "test", " ", "test", "url", "pass", VALID_ROLE_SET,
            VALID_SKILL_SET, VALID_TIME, VALID_TIME))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenEmailIsBlank() {
    assertThatThrownBy(
        () -> new User(VALID_UUID, "test", "test", null, "url", "pass", VALID_ROLE_SET,
            VALID_SKILL_SET, VALID_TIME, VALID_TIME))
        .isInstanceOf(DomainValidationException.class);
    assertThatThrownBy(
        () -> new User(VALID_UUID, "test", "test", "  ", "url", "pass", VALID_ROLE_SET,
            VALID_SKILL_SET, VALID_TIME, VALID_TIME))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenPasswordIsBlank() {
    assertThatThrownBy(
        () -> new User(VALID_UUID, "test", "test", "test", "url", null, VALID_ROLE_SET,
            VALID_SKILL_SET, VALID_TIME, VALID_TIME))
        .isInstanceOf(DomainValidationException.class);
    assertThatThrownBy(
        () -> new User(VALID_UUID, "test", "test", "test", "url", "  ", VALID_ROLE_SET,
            VALID_SKILL_SET, VALID_TIME, VALID_TIME))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenRolesAreNull() {
    assertThatThrownBy(
        () -> new User(VALID_UUID, "test", "test", "test", "url", "pass", null, VALID_SKILL_SET,
            VALID_TIME, VALID_TIME))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenCreatedAtIsNull() {
    assertThatThrownBy(
        () -> new User(VALID_UUID, "test", "test", "test", "url", "pass", VALID_ROLE_SET,
            VALID_SKILL_SET, null, VALID_TIME))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldThrowExceptionWhenUpdatedAtIsNull() {
    assertThatThrownBy(
        () -> new User(VALID_UUID, "test", "test", "test", "url", "pass", VALID_ROLE_SET,
            VALID_SKILL_SET, VALID_TIME, null))
        .isInstanceOf(DomainValidationException.class);
  }

  @Test
  public void shouldNotThrowExceptionWhenAvatarUrlIsNull() {
    assertThatCode(
        () -> new User(VALID_UUID, "test", "test", "test", null, "pass", VALID_ROLE_SET,
            VALID_SKILL_SET, VALID_TIME, VALID_TIME))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldNotThrowExceptionWhenSkillsAreNull() {
    assertThatCode(
        () -> new User(VALID_UUID, "test", "test", "test", "url", "pass", VALID_ROLE_SET, null,
            VALID_TIME, VALID_TIME))
        .doesNotThrowAnyException();
  }

  @Test
  public void shouldReturnTrueWhenRoleIsPresent() {
    var roles = new HashSet<Role>();
    roles.add(new Role(1, Role.ADMIN_ROLE_NAME));
    var user = new User(VALID_UUID, "test", "test", "test", null, "pass", roles, null, VALID_TIME,
        VALID_TIME);
    assertThat(user.hasRole("ADMIN")).isTrue();
  }

  @Test
  public void shouldReturnFalseWhenRoleIsAbsent() {
    var roles = new HashSet<Role>();
    roles.add(new Role(1, Role.USER_ROLE_NAME));
    var user = new User(VALID_UUID, "test", "test", "test", null, "pass", roles, null, VALID_TIME,
        VALID_TIME);
    assertThat(user.hasRole("ADMIN")).isFalse();
  }

  @Test
  public void shouldReturnTrueForRoleCaseInsensitive() {
    var roles = new HashSet<Role>();
    roles.add(new Role(1, "Admin"));
    var user = new User(VALID_UUID, "test", "test", "test", null, "pass", roles, null, VALID_TIME,
        VALID_TIME);
    assertThat(user.hasRole("admin")).isTrue();
  }

  @Test
  public void shouldReturnFalseWhenRolesSetIsEmpty() {
    var user = new User(VALID_UUID, "test", "test", "test", null, "pass", VALID_ROLE_SET, null,
        VALID_TIME, VALID_TIME);
    assertThat(user.hasRole("ADMIN")).isFalse();
  }

  @Test
  public void shouldThrowExceptionWhenIdIsNullWithCustomMessage() {
    assertThatThrownBy(
        () -> new User(null, "test", "test", "test", "url", "pass", VALID_ROLE_SET, VALID_SKILL_SET,
            VALID_TIME, VALID_TIME))
        .hasMessageContaining("User ID must not be null");
  }
}
