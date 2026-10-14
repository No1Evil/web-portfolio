import { httpResource } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import type { UserEducationPathAdminResponse } from '@api/index';
import { API_BASE_PATH } from '@core/config/api';
import { resourceState } from '@core/state/resource-state';
import { AdminApiService } from './admin-api.service';
import type { EducationPathPayload } from './admin.models';

@Injectable({ providedIn: 'root' })
export class EducationStore {
  private readonly api = inject(AdminApiService);

  private readonly educationResource = httpResource<UserEducationPathAdminResponse[]>(() => ({
    url: `${API_BASE_PATH}/admin/profile/education-paths`,
    method: 'GET',
  }));

  readonly education = resourceState(this.educationResource.status, this.educationResource.value);

  add(request: EducationPathPayload): void {
    this.api.createEducation(request).subscribe(() => this.educationResource.reload());
  }

  update(id: string, request: EducationPathPayload): void {
    this.api.updateEducation(id, request).subscribe(() => this.educationResource.reload());
  }

  remove(id: string): void {
    this.api.deleteEducation(id).subscribe(() => this.educationResource.reload());
  }
}