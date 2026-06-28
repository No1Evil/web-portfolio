package dev.tsumakov.application.portfolio.usecase.skillcategory;

import static org.mockito.Mockito.verify;

import dev.tsumakov.domain.portfolio.repository.SkillCategoriesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteSkillCategoryUseCaseImplTest {

  @Mock
  private SkillCategoriesRepository skillCategoriesRepository;

  private DeleteSkillCategoryUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new DeleteSkillCategoryUseCaseImpl(skillCategoriesRepository);
  }

  @Test
  void shouldDeleteById() {
    useCase.execute(1);
    verify(skillCategoriesRepository).deleteById(1);
  }
}
