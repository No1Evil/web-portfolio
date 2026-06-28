package dev.tsumakov.application.portfolio.usecase.skillcategory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import dev.tsumakov.application.portfolio.dto.in.CreateSkillCategoryDto;
import dev.tsumakov.application.portfolio.mapper.SkillCategoryDtoMapperImpl;
import dev.tsumakov.domain.portfolio.repository.SkillCategoriesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateSkillCategoryUseCaseImplTest {

  @Mock
  private SkillCategoriesRepository skillCategoriesRepository;

  private CreateSkillCategoryUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new CreateSkillCategoryUseCaseImpl(skillCategoriesRepository,
        new SkillCategoryDtoMapperImpl());
  }

  @Test
  void shouldCreateSkillCategory() {
    var command = new CreateSkillCategoryDto("Backend", "icon.png");

    var result = useCase.execute(command);

    assertThat(result.id()).isNull();
    assertThat(result.name()).isEqualTo("Backend");
    assertThat(result.iconUrl()).isEqualTo("icon.png");
    verify(skillCategoriesRepository).save(any());
  }
}
