import { httpResource } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import type { UserExperiencePathAdminResponse } from '@api/index';
import { API_BASE_PATH } from '@core/config/api';
import { resourceState } from '@core/state/resource-state';
import { AdminApiService } from './admin-api.service';
import type { ExperiencePathPayload } from './admin.models';

@Injectable({ providedIn: 'root' })
export class ExperienceStore {
  private readonly api = inject(AdminApiService);

  private readonly experienceResource = httpResource<UserExperiencePathAdminResponse[]>(() => ({
    url: `${API_BASE_PATH}/admin/profile/experience-paths`,
    method: 'GET',
  }));

  readonly experience = resourceState(this.experienceResource.status, this.experienceResource.value);

  add(request: ExperiencePathPayload): void {
    this.api.createExperience(request).subscribe(() => this.experienceResource.reload());
  }

  update(id: string, request: ExperiencePathPayload): void {
    this.api.updateExperience(id, request).subscribe(() => this.experienceResource.reload());
  }

  remove(id: string): void {
    this.api.deleteExperience(id).subscribe(() => this.experienceResource.reload());
  }
}