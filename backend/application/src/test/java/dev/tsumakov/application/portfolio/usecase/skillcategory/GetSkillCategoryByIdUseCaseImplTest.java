package dev.tsumakov.application.portfolio.usecase.skillcategory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.portfolio.mapper.SkillCategoryDtoMapperImpl;
import dev.tsumakov.domain.portfolio.model.SkillCategory;
import dev.tsumakov.domain.portfolio.repository.SkillCategoriesRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetSkillCategoryByIdUseCaseImplTest {

  @Mock
  private SkillCategoriesRepository skillCategoriesRepository;

  private GetSkillCategoryByIdUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new GetSkillCategoryByIdUseCaseImpl(skillCategoriesRepository,
        new SkillCategoryDtoMapperImpl());
  }

  @Test
  void shouldReturnCategoryWhenFound() {
    when(skillCategoriesRepository.findById(1)).thenReturn(Optional.of(new SkillCategory(1, "Backend", null)));

    var result = useCase.execute(1);

    assertThat(result).isPresent();
    assertThat(result.get().name()).isEqualTo("Backend");
  }

  @Test
  void shouldReturnEmptyWhenNotFound() {
    when(skillCategoriesRepository.findById(99)).thenReturn(Optional.empty());

    var result = useCase.execute(99);

    assertThat(result).isEmpty();
  }
}
