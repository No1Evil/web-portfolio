import { ChangeDetectionStrategy, Component, computed, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { I18nService } from '@core/i18n/i18n.service';
import { unwrap } from '@core/state/async-state';
import type { UserContactAdminResponse } from '@api/index';
import { EntryList } from '../../components/entry-list/entry-list';
import { CONTACT_TYPES, type EntryRow } from '../../data/admin.models';
import { ContactsStore } from '../../data/contacts.store';

@Component({
  selector: 'app-admin-contacts',
  imports: [FormsModule, EntryList],
  templateUrl: './contacts.html',
  styleUrl: './contacts.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ContactsPage {
  private readonly i18n = inject(I18nService);
  private readonly contacts = inject(ContactsStore);

  protected readonly dict = this.i18n.dictionary;
  protected readonly entries = this.contacts.contacts;
  protected readonly types = CONTACT_TYPES;

  protected readonly editingId = signal<number | null>(null);
  protected readonly type = signal<string>(CONTACT_TYPES[0]);
  protected readonly value = signal('');

  protected readonly rows = computed<EntryRow[]>(() => {
    const items = unwrap(this.contacts.contacts()) ?? [];
    return items.map((item) => ({
      id: item.id ?? 0,
      title: item.title ?? '',
      subtitle: item.redirectUrl ?? '',
    }));
  });

  protected startEdit(item: UserContactAdminResponse): void {
    this.editingId.set(item.id ?? null);
    this.type.set(item.title ?? CONTACT_TYPES[0]);
    this.value.set(item.redirectUrl ?? '');
  }

  protected editFromRow(row: EntryRow): void {
    const item = (unwrap(this.contacts.contacts()) ?? []).find((entry) => entry.id === row.id);
    if (item) {
      this.startEdit(item);
    }
  }

  protected startAdd(): void {
    this.editingId.set(null);
    this.type.set(CONTACT_TYPES[0]);
    this.value.set('');
  }

  protected cancel(): void {
    this.editingId.set(null);
  }

  protected submit(): void {
    const payload = { title: this.type().trim(), redirectUrl: this.value().trim() };
    const id = this.editingId();
    if (id !== null) {
      this.contacts.update(id, payload);
    } else {
      this.contacts.add(payload);
    }
    this.cancel();
  }

  protected remove(row: EntryRow): void {
    this.contacts.remove(Number(row.id));
  }
}