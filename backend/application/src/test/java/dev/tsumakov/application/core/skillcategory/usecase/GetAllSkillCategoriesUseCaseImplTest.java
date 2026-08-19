package dev.tsumakov.application.core.skillcategory.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.tsumakov.application.core.skillcategory.dto.outer.SkillCategoryDto;
import dev.tsumakov.application.core.skillcategory.mapper.SkillCategoryDtoMapper;
import dev.tsumakov.domain.core.skillcategory.model.SkillCategory;
import dev.tsumakov.domain.core.skillcategory.repository.SkillCategoryRepository;
import java.time.OffsetDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetAllSkillCategoriesUseCaseImplTest {

  private static final OffsetDateTime NOW = OffsetDateTime.now();

  @Mock
  private SkillCategoryRepository repository;
  @Mock
  private SkillCategoryDtoMapper mapper;

  private GetAllSkillCategoriesUseCaseImpl useCase;

  @BeforeEach
  void setUp() {
    useCase = new GetAllSkillCategoriesUseCaseImpl(repository, mapper);
  }

  @Test
  void shouldReturnAllCategoriesMapped() {
    var backend = new SkillCategory(1, "Backend", "icon.svg", 1L, NOW, NOW);
    var frontend = new SkillCategory(2, "Frontend", null, 1L, NOW, NOW);
    var backendDto = new SkillCategoryDto(1, "Backend", "icon.svg", NOW, NOW);
    var frontendDto = new SkillCategoryDto(2, "Frontend", null, NOW, NOW);

    when(repository.findAll()).thenReturn(List.of(backend, frontend));
    when(mapper.toDto(backend)).thenReturn(backendDto);
    when(mapper.toDto(frontend)).thenReturn(frontendDto);

    var result = useCase.execute();

    assertThat(result).containsExactly(backendDto, frontendDto);
    verify(mapper).toDto(backend);
    verify(mapper).toDto(frontend);
  }

  @Test
  void shouldReturnEmptyListWhenRepositoryEmpty() {
    when(repository.findAll()).thenReturn(List.of());

    var result = useCase.execute();

    assertThat(result).isEmpty();
    verify(mapper, never()).toDto(org.mockito.ArgumentMatchers.any());
  }
}