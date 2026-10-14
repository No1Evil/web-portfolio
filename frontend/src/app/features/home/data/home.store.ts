import { computed, Injectable } from '@angular/core';
import { httpResource } from '@angular/common/http';
import type {
  SkillCategoryUserResponse,
  UserSkillResponse,
  UserSummaryResponse,
} from '@api/index';
import { API_BASE_PATH } from '@core/config/api';
import type { StackGroup, HeroState, StackState } from './home.models';

function groupByCategory(
  categories: SkillCategoryUserResponse[],
  skills: UserSkillResponse[],
): StackGroup[] {
  return categories.map((category) => ({
    category,
    skills: skills.filter((skill) => skill.categoryId === category.id),
  }));
}

@Injectable({ providedIn: 'root' })
export class HomeStore {
  private readonly summaryResource = httpResource<UserSummaryResponse>(
    () => ({ url: `${API_BASE_PATH}/profile/summary`, method: 'GET' }),
  );

  private readonly skillsResource = httpResource<UserSkillResponse[]>(
    () => ({ url: `${API_BASE_PATH}/profile/skills`, method: 'GET' }),
  );

  private readonly categoriesResource = httpResource<SkillCategoryUserResponse[]>(
    () => ({ url: `${API_BASE_PATH}/skill-categories`, method: 'GET' }),
  );

  readonly hero = computed<HeroState>(() => {
    const status = this.summaryResource.status();
    if (status === 'error') return { status: 'empty' };
    if (status === 'resolved' || status === 'local') {
      const value = this.summaryResource.value();
      return value ? { status: 'resolved', value } : { status: 'empty' };
    }
    return { status: 'loading' };
  });

  readonly stack = computed<StackState>(() => {
    const categoriesStatus = this.categoriesResource.status();
    const skillsStatus = this.skillsResource.status();
    if (categoriesStatus === 'error' || skillsStatus === 'error') {
      return { status: 'empty' };
    }
    if (categoriesStatus === 'resolved' && skillsStatus === 'resolved') {
      const value = groupByCategory(
        this.categoriesResource.value() ?? [],
        this.skillsResource.value() ?? [],
      );
      return value.some((group) => group.skills.length > 0)
        ? { status: 'resolved', value }
        : { status: 'empty' };
    }
    return { status: 'loading' };
  });
}