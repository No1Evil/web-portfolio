import { ChangeDetectionStrategy, Component, computed, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { I18nService } from '@core/i18n/i18n.service';
import { unwrap } from '@core/state/async-state';
import type { UserExperiencePathAdminResponse } from '@api/index';
import { EntryList } from '../../components/entry-list/entry-list';
import type { EntryRow } from '../../data/admin.models';
import { formatPeriod, toDateInput, toIso } from '../../data/dates';
import { ExperienceStore } from '../../data/experience.store';

@Component({
  selector: 'app-admin-experience',
  imports: [FormsModule, EntryList],
  templateUrl: './experience.html',
  styleUrl: './experience.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ExperiencePage {
  private readonly i18n = inject(I18nService);
  private readonly experience = inject(ExperienceStore);

  protected readonly dict = this.i18n.dictionary;
  protected readonly editingLocale = this.i18n.editingLocale;
  protected readonly entries = this.experience.experience;

  protected readonly editingId = signal<string | null>(null);
  protected readonly role = signal('');
  protected readonly company = signal('');
  protected readonly location = signal('');
  protected readonly startDate = signal('');
  protected readonly endDate = signal('');
  protected readonly present = signal(false);
  protected readonly descriptions = signal<Record<string, string>>({});

  protected readonly description = computed(() => this.descriptions()[this.editingLocale()] ?? '');

  protected readonly rows = computed<EntryRow[]>(() => {
    const presentLabel = this.dict()['admin']['present'];
    const items = unwrap(this.experience.experience()) ?? [];
    return items.map((item) => ({
      id: item.id ?? '',
      title: item.title ?? '',
      subtitle: [item.companyName, item.location].filter(Boolean).join(' · '),
      meta: formatPeriod(item.startDate, item.endDate, item.present, presentLabel),
    }));
  });

  protected startEdit(item: UserExperiencePathAdminResponse): void {
    this.editingId.set(item.id ?? null);
    this.role.set(item.title ?? '');
    this.company.set(item.companyName ?? '');
    this.location.set(item.location ?? '');
    this.startDate.set(toDateInput(item.startDate));
    this.endDate.set(toDateInput(item.endDate));
    this.present.set(item.present ?? false);
    this.descriptions.set({ ...(item.description ?? {}) });
  }

  protected editFromRow(row: EntryRow): void {
    const item = (unwrap(this.experience.experience()) ?? []).find((entry) => entry.id === row.id);
    if (item) {
      this.startEdit(item);
    }
  }

  protected startAdd(): void {
    this.editingId.set(null);
    this.role.set('');
    this.company.set('');
    this.location.set('');
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
      title: this.role().trim(),
      companyName: this.company().trim(),
      location: this.location().trim(),
      description: this.descriptions(),
      present: this.present(),
      startDate: toIso(this.startDate()),
      endDate: this.present() ? undefined : toIso(this.endDate()),
    };
    const id = this.editingId();
    if (id) {
      this.experience.update(id, payload);
    } else {
      this.experience.add(payload);
    }
    this.cancel();
  }

  protected remove(row: EntryRow): void {
    this.experience.remove(String(row.id));
  }
}