import { httpResource } from '@angular/common/http';
import { computed, inject, Injectable } from '@angular/core';
import { of, switchMap, tap } from 'rxjs';
import type {
  CreateSkillCategoryRequest,
  SkillCategoryAdminResponse,
  UpdateSkillCategoryRequest,
  UserSkillResponse,
} from '@api/index';
import { API_BASE_PATH } from '@core/config/api';
import { AuthService } from '@core/auth/auth.service';
import { resourceState } from '@core/state/resource-state';
import type { AsyncState } from '@core/state/async-state';
import { AdminApiService } from './admin-api.service';
import type { SkillGroup } from './admin.models';

@Injectable({ providedIn: 'root' })
export class SkillsStore {
  private readonly api = inject(AdminApiService);
  private readonly auth = inject(AuthService);

  private readonly categoriesResource = httpResource<SkillCategoryAdminResponse[]>(() => ({
    url: `${API_BASE_PATH}/admin/skill-categories`,
    method: 'GET',
  }));

  private readonly userSkillsResource = httpResource<UserSkillResponse[]>(() => ({
    url: `${API_BASE_PATH}/admin/profile/skills`,
    method: 'GET',
  }));

  readonly categories = resourceState(this.categoriesResource.status, this.categoriesResource.value);
  readonly userSkills = resourceState(this.userSkillsResource.status, this.userSkillsResource.value);

  readonly groups = computed<AsyncState<SkillGroup[]>>(() => {
    const categories = this.categories();
    const skills = this.userSkills();
    if (categories.status !== 'resolved' || skills.status !== 'resolved') {
      return { status: 'loading' };
    }
    return {
      status: 'resolved',
      value: categories.value.map((category) => ({
        category,
        skills: skills.value.filter((skill) => skill.categoryId === category.id),
      })),
    };
  });

  add(categoryId: number, name: string): void {
    const userId = this.auth.user()?.id;
    if (userId === undefined) return;
    this.api
      .createSkill({ categoryId, name })
      .pipe(
        switchMap((skill) =>
          skill.id === undefined
            ? of(null)
            : this.api.assignSkillToUser({ userId, skillId: skill.id }),
        ),
        tap(() => this.userSkillsResource.reload()),
      )
      .subscribe();
  }

  update(id: number, name: string): void {
    this.api.updateSkill(id, { name }).subscribe(() => this.userSkillsResource.reload());
  }

  remove(id: number): void {
    const userId = this.auth.user()?.id;
    if (userId === undefined) return;
    this.api
      .unassignSkillFromUser({ userId, skillId: id })
      .subscribe(() => this.userSkillsResource.reload());
  }

  addCategory(request: CreateSkillCategoryRequest): void {
    this.api.createCategory(request).subscribe(() => this.categoriesResource.reload());
  }

  updateCategory(id: number, request: UpdateSkillCategoryRequest): void {
    this.api.updateCategory(id, request).subscribe(() => this.categoriesResource.reload());
  }

  removeCategory(id: number): void {
    this.api.deleteCategory(id).subscribe(() => {
      this.categoriesResource.reload();
      this.userSkillsResource.reload();
    });
  }
}