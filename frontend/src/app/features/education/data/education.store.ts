import { computed, Injectable } from '@angular/core';
import { httpResource } from '@angular/common/http';
import type { UserEducationPathResponse } from '@api/index';
import { API_BASE_PATH } from '@core/config/api';
import type { AsyncState } from '@core/state/async-state';

@Injectable({ providedIn: 'root' })
export class EducationStore {
  private readonly educationResource = httpResource<UserEducationPathResponse[]>(
    () => ({ url: `${API_BASE_PATH}/profile/education-paths`, method: 'GET' }),
  );

  readonly education = computed<AsyncState<UserEducationPathResponse[]>>(() => {
    const status = this.educationResource.status();
    if (status === 'error') return { status: 'empty' };
    if (status === 'resolved' || status === 'local') {
      const value = this.educationResource.value();
      return value && value.length > 0 ? { status: 'resolved', value } : { status: 'empty' };
    }
    return { status: 'loading' };
  });
}