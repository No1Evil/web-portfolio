import { httpResource } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import type { UpdateUserSummaryRequest, UserSummaryAdminResponse } from '@api/index';
import { API_BASE_PATH } from '@core/config/api';
import { resourceState } from '@core/state/resource-state';
import { AdminApiService } from './admin-api.service';

@Injectable({ providedIn: 'root' })
export class SummaryStore {
  private readonly api = inject(AdminApiService);

  private readonly summaryResource = httpResource<UserSummaryAdminResponse>(() => ({
    url: `${API_BASE_PATH}/admin/profile/summary`,
    method: 'GET',
  }));

  readonly summary = resourceState(this.summaryResource.status, this.summaryResource.value);

  create(request: UpdateUserSummaryRequest): void {
    this.api.createSummary(request).subscribe(() => this.summaryResource.reload());
  }

  update(request: UpdateUserSummaryRequest): void {
    this.api.updateSummary(request).subscribe(() => this.summaryResource.reload());
  }
}