package dev.tsumakov.application.core.skillcategory.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.core.skillcategory.dto.outer.SkillCategoryDto;
import dev.tsumakov.application.core.skillcategory.exception.SkillCategoryNotFoundException;
import dev.tsumakov.application.core.skillcategory.mapper.SkillCategoryDtoMapper;
import dev.tsumakov.domain.core.skillcategory.model.SkillCategory;
import dev.tsumakov.domain.core.skillcategory.repository.SkillCategoryRepository;
import java.time.OffsetDateTime;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetSkillCategoryByIdUseCaseImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.now();

  @Mock
  private SkillCategoryRepository repository;
  @Mock
  private SkillCategoryDtoMapper mapper;

  private GetSkillCategoryByIdUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new GetSkillCategoryByIdUseCaseImpl(repository, mapper);
  }

  @Test
  void shouldReturnCategoryDtoWhenFound() {
    var category = new SkillCategory(1, "Backend", "icon.svg", 2L, NOW, NOW);
    var expectedDto = new SkillCategoryDto(1, "Backend", "icon.svg", NOW, NOW);

    when(repository.findById(1)).thenReturn(Optional.of(category));
    when(mapper.toDto(category)).thenReturn(expectedDto);

    var result = useCase.execute(1);

    assertThat(result).isEqualTo(expectedDto);
  }

  @Test
  void shouldThrowNotFoundExceptionWhenCategoryMissing() {
    when(repository.findById(99)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> useCase.execute(99))
        .isInstanceOf(SkillCategoryNotFoundException.class)
        .hasMessage("Skill category with id 99 not found");

    verify(mapper, never()).toDto(org.mockito.ArgumentMatchers.any());
  }
}