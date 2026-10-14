import { httpResource } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import type { CreateUserContactRequest, UpdateUserContactRequest, UserContactAdminResponse } from '@api/index';
import { API_BASE_PATH } from '@core/config/api';
import { resourceState } from '@core/state/resource-state';
import { AdminApiService } from './admin-api.service';

@Injectable({ providedIn: 'root' })
export class ContactsStore {
  private readonly api = inject(AdminApiService);

  private readonly contactsResource = httpResource<UserContactAdminResponse[]>(() => ({
    url: `${API_BASE_PATH}/admin/profile/contacts`,
    method: 'GET',
  }));

  readonly contacts = resourceState(this.contactsResource.status, this.contactsResource.value);

  add(request: CreateUserContactRequest): void {
    this.api.createContact(request).subscribe(() => this.contactsResource.reload());
  }

  update(id: number, request: UpdateUserContactRequest): void {
    this.api.updateContact(id, request).subscribe(() => this.contactsResource.reload());
  }

  remove(id: number): void {
    this.api.deleteContact(id).subscribe(() => this.contactsResource.reload());
  }
}