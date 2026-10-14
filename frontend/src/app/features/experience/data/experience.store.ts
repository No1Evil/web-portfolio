import { computed, Injectable } from '@angular/core';
import { httpResource } from '@angular/common/http';
import type { UserExperiencePathResponse } from '@api/index';
import { API_BASE_PATH } from '@core/config/api';
import type { AsyncState } from '@core/state/async-state';

@Injectable({ providedIn: 'root' })
export class ExperienceStore {
  private readonly experienceResource = httpResource<UserExperiencePathResponse[]>(
    () => ({ url: `${API_BASE_PATH}/profile/experience-paths`, method: 'GET' }),
  );

  readonly experience = computed<AsyncState<UserExperiencePathResponse[]>>(() => {
    const status = this.experienceResource.status();
    if (status === 'error') return { status: 'empty' };
    if (status === 'resolved' || status === 'local') {
      const value = this.experienceResource.value();
      return value && value.length > 0 ? { status: 'resolved', value } : { status: 'empty' };
    }
    return { status: 'loading' };
  });
}