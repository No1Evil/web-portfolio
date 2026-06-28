package dev.tsumakov.application.portfolio.usecase.skillcategory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.portfolio.mapper.SkillCategoryDtoMapperImpl;
import dev.tsumakov.domain.portfolio.model.SkillCategory;
import dev.tsumakov.domain.portfolio.repository.SkillCategoriesRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetAllSkillCategoriesUseCaseImplTest {

  @Mock
  private SkillCategoriesRepository skillCategoriesRepository;

  private GetAllSkillCategoriesUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new GetAllSkillCategoriesUseCaseImpl(skillCategoriesRepository,
        new SkillCategoryDtoMapperImpl());
  }

  @Test
  void shouldReturnAllCategories() {
    when(skillCategoriesRepository.findAll())
        .thenReturn(List.of(new SkillCategory(1, "Backend", null), new SkillCategory(2, "Frontend", null)));

    var result = useCase.execute();

    assertThat(result).hasSize(2);
    assertThat(result).extracting("name").containsExactly("Backend", "Frontend");
  }

  @Test
  void shouldReturnEmptyListWhenNoCategories() {
    when(skillCategoriesRepository.findAll()).thenReturn(List.of());

    var result = useCase.execute();

    assertThat(result).isEmpty();
  }
}
