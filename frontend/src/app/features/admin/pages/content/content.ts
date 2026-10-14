import { ChangeDetectionStrategy, Component, computed, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { I18nService } from '@core/i18n/i18n.service';
import { unwrap } from '@core/state/async-state';
import { SummaryStore } from '../../data/summary.store';

@Component({
  selector: 'app-admin-content',
  imports: [FormsModule],
  templateUrl: './content.html',
  styleUrl: './content.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ContentPage {
  private readonly i18n = inject(I18nService);
  private readonly summary = inject(SummaryStore);

  protected readonly dict = this.i18n.dictionary;
  protected readonly editingLocale = this.i18n.editingLocale;

  protected readonly firstName = signal('');
  protected readonly lastName = signal('');
  protected readonly role = signal('');
  protected readonly descriptions = signal<Record<string, string>>({});
  protected readonly saved = signal(false);

  protected readonly description = computed(() => this.descriptions()[this.editingLocale()] ?? '');

  protected readonly data = computed(() => {
    const value = unwrap(this.summary.summary());
    if (value) {
      this.firstName.set(value.firstName ?? '');
      this.lastName.set(value.lastName ?? '');
      this.role.set(value.proficiency ?? '');
      this.descriptions.set({ ...(value.description ?? {}) });
    }
    return value;
  });

  protected onDescriptionChange(value: string): void {
    this.descriptions.update((current) => ({ ...current, [this.editingLocale()]: value }));
  }

  protected submit(): void {
    const existing = unwrap(this.summary.summary());
    const description = { ...this.descriptions() };
    if (!existing && this.editingLocale() !== 'en') {
      description['en'] = description['en'] ?? description[this.editingLocale()];
    }
    const request = {
      firstName: this.firstName(),
      lastName: this.lastName(),
      proficiency: this.role(),
      description,
    };
    if (existing) {
      this.summary.update(request);
    } else {
      this.summary.create(request);
    }
    this.saved.set(true);
    window.setTimeout(() => this.saved.set(false), 2000);
  }
}