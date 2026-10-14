import { computed, Injectable } from '@angular/core';
import { httpResource } from '@angular/common/http';
import type { UserContactResponse } from '@api/index';
import { API_BASE_PATH } from '@core/config/api';
import type { AsyncState } from '@core/state/async-state';

@Injectable({ providedIn: 'root' })
export class ContactStore {
  private readonly contactsResource = httpResource<UserContactResponse[]>(
    () => ({ url: `${API_BASE_PATH}/profile/contacts`, method: 'GET' }),
  );

  readonly contacts = computed<AsyncState<UserContactResponse[]>>(() => {
    const status = this.contactsResource.status();
    if (status === 'error') return { status: 'empty' };
    if (status === 'resolved' || status === 'local') {
      const value = this.contactsResource.value();
      return value && value.length > 0 ? { status: 'resolved', value } : { status: 'empty' };
    }
    return { status: 'loading' };
  });
}