package dev.tsumakov.application.profile.skill.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.profile.skill.dto.in.AssignSkillToUserDto;
import dev.tsumakov.domain.core.skill.repository.UserSkillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AssignSkillToUserUseCaseImplTest {

  @Mock
  private UserSkillRepository repository;

  private AssignSkillToUserUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new AssignSkillToUserUseCaseImpl(repository);
  }

  @Test
  void shouldAssignSkillToUser() {
    useCase.execute(new AssignSkillToUserDto(1, 3));

    verify(repository).addSkill(1, 3);
  }

  @Test
  void shouldThrowWhenRepositoryFails() {
    org.mockito.Mockito.doThrow(new RuntimeException("duplicate"))
        .when(repository).addSkill(1, 3);

    assertThatThrownBy(() -> useCase.execute(new AssignSkillToUserDto(1, 3)))
        .isInstanceOf(RuntimeException.class)
        .hasMessage("duplicate");
  }
}