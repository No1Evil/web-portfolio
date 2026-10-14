import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { I18nService } from '@core/i18n/i18n.service';
import type { Locale } from '@core/i18n/translations';

const LOCALES: readonly { code: Locale; label: string }[] = [
  { code: 'en', label: 'EN' },
  { code: 'ru', label: 'RU' },
  { code: 'et', label: 'ET' },
];

@Component({
  selector: 'app-admin-locale-switcher',
  imports: [],
  templateUrl: './admin-locale-switcher.html',
  styleUrl: './admin-locale-switcher.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AdminLocaleSwitcher {
  private readonly i18n = inject(I18nService);

  protected readonly locales = LOCALES;
  protected readonly editingLocale = this.i18n.editingLocale;

  protected select(locale: Locale): void {
    this.i18n.setEditingLocale(locale);
  }
}