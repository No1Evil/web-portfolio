import { ChangeDetectionStrategy, Component, computed, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { I18nService } from '@core/i18n/i18n.service';
import { unwrap } from '@core/state/async-state';
import type { UserEducationPathAdminResponse } from '@api/index';
import { EntryList } from '../../components/entry-list/entry-list';
import type { EntryRow } from '../../data/admin.models';
import { formatPeriod, toDateInput, toIso } from '../../data/dates';
import { EducationStore } from '../../data/education.store';

@Component({
  selector: 'app-admin-education',
  imports: [FormsModule, EntryList],
  templateUrl: './education.html',
  styleUrl: './education.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class EducationPage {
  private readonly i18n = inject(I18nService);
  private readonly education = inject(EducationStore);

  protected readonly dict = this.i18n.dictionary;
  protected readonly editingLocale = this.i18n.editingLocale;
  protected readonly entries = this.education.education;

  protected readonly editingId = signal<string | null>(null);
  protected readonly degree = signal('');
  protected readonly institution = signal('');
  protected readonly startDate = signal('');
  protected readonly endDate = signal('');
  protected readonly present = signal(false);
  protected readonly descriptions = signal<Record<string, string>>({});

  protected readonly description = computed(() => this.descriptions()[this.editingLocale()] ?? '');

  protected readonly rows = computed<EntryRow[]>(() => {
    const presentLabel = this.dict()['admin']['present'];
    const items = unwrap(this.education.education()) ?? [];
    return items.map((item) => ({
      id: item.id ?? '',
      title: item.title ?? '',
      subtitle: item.location ?? '',
      meta: formatPeriod(item.startDate, item.endDate, item.present, presentLabel),
    }));
  });

  protected startEdit(item: UserEducationPathAdminResponse): void {
    this.editingId.set(item.id ?? null);
    this.degree.set(item.title ?? '');
    this.institution.set(item.location ?? '');
    this.startDate.set(toDateInput(item.startDate));
    this.endDate.set(toDateInput(item.endDate));
    this.present.set(item.present ?? false);
    this.descriptions.set({ ...(item.description ?? {}) });
  }

  protected startAdd(): void {
    this.editingId.set(null);
    this.degree.set('');
    this.institution.set('');
    this.startDate.set('');
    this.endDate.set('');
    this.present.set(false);
    this.descriptions.set({});
  }

  protected cancel(): void {
    this.editingId.set(null);
  }

  protected onDescriptionChange(value: string): void {
    this.descriptions.update((current) => ({ ...current, [this.editingLocale()]: value }));
  }

  protected submit(): void {
    const payload = {
      title: this.degree().trim(),
      location: this.institution().trim(),
      description: this.descriptions(),
      present: this.present(),
      startDate: toIso(this.startDate()),
      endDate: this.present() ? undefined : toIso(this.endDate()),
    };
    const id = this.editingId();
    if (id) {
      this.education.update(id, payload);
    } else {
      this.education.add(payload);
    }
    this.cancel();
  }

  protected editFromRow(row: EntryRow): void {
    const item = (unwrap(this.education.education()) ?? []).find((entry) => entry.id === row.id);
    if (item) {
      this.startEdit(item);
    }
  }

  protected remove(row: EntryRow): void {
    this.education.remove(String(row.id));
  }
}