package dev.tsumakov.application.profile.skill.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.profile.skill.dto.in.UnassignSkillFromUserDto;
import dev.tsumakov.domain.core.skill.repository.UserSkillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UnassignSkillFromUserUseCaseImplTest {

  @Mock
  private UserSkillRepository repository;

  private UnassignSkillFromUserUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new UnassignSkillFromUserUseCaseImpl(repository);
  }

  @Test
  void shouldUnassignSkillFromUser() {
    useCase.execute(new UnassignSkillFromUserDto(1, 3));

    verify(repository).removeSkill(1, 3);
  }

  @Test
  void shouldThrowWhenRepositoryFails() {
    org.mockito.Mockito.doThrow(new RuntimeException("not found"))
        .when(repository).removeSkill(1, 3);

    assertThatThrownBy(() -> useCase.execute(new UnassignSkillFromUserDto(1, 3)))
        .isInstanceOf(RuntimeException.class)
        .hasMessage("not found");
  }
}