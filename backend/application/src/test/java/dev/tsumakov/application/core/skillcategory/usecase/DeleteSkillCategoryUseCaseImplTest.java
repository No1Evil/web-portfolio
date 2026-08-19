package dev.tsumakov.application.core.skillcategory.usecase;

import static org.mockito.Mockito.verify;

import dev.tsumakov.application.core.skillcategory.port.in.DeleteSkillCategoryUseCase;
import dev.tsumakov.domain.core.skillcategory.repository.SkillCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteSkillCategoryUseCaseImplTest {

  @Mock
  private SkillCategoryRepository repository;

  private DeleteSkillCategoryUseCase useCase;

  @BeforeEach
  void setUp() {
    useCase = new DeleteSkillCategoryUseCaseImpl(repository);
  }

  @Test
  void shouldDeleteCategoryById() {
    useCase.execute(5);

    verify(repository).delete(5);
  }
}