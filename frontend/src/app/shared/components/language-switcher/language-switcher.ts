import { ChangeDetectionStrategy, Component, inject, signal } from '@angular/core';
import { I18nService } from '@core/i18n/i18n.service';
import type { Locale } from '@core/i18n/translations';

const LOCALES: readonly { code: Locale; label: string }[] = [
  { code: 'en', label: 'English' },
  { code: 'ru', label: 'Русский' },
  { code: 'et', label: 'Eesti' },
];

@Component({
  selector: 'app-language-switcher',
  imports: [],
  templateUrl: './language-switcher.html',
  styleUrl: './language-switcher.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class LanguageSwitcher {
  private readonly i18n = inject(I18nService);

  protected readonly dict = this.i18n.dictionary;
  protected readonly locale = this.i18n.locale;
  protected readonly localeCode = this.i18n.localeCode;
  protected readonly locales = LOCALES;

  protected readonly open = signal(false);

  protected toggle(): void {
    this.open.update((isOpen) => !isOpen);
  }

  protected close(): void {
    this.open.set(false);
  }

  protected select(locale: Locale): void {
    this.i18n.setLocale(locale);
    this.open.set(false);
  }
}
