import { computed, Injectable, signal } from '@angular/core';
import { translations, type I18nDictionary, type Locale } from './translations';

const LOCALE_STORAGE_KEY = 'locale';

@Injectable({ providedIn: 'root' })
export class I18nService {
  private readonly localeSignal = signal<Locale>(this.detectLocale());
  private readonly editingLocaleSignal = signal<Locale>('en');

  readonly locale = this.localeSignal.asReadonly();
  readonly editingLocale = this.editingLocaleSignal.asReadonly();
  readonly dictionary = computed<I18nDictionary>(() => translations[this.locale()]);
  readonly localeCode = computed(() => this.locale().toUpperCase());

  setLocale(locale: Locale): void {
    this.localeSignal.set(locale);
    localStorage.setItem(LOCALE_STORAGE_KEY, locale);
  }

  setEditingLocale(locale: Locale): void {
    this.editingLocaleSignal.set(locale);
  }

  private detectLocale(): Locale {
    const saved = localStorage.getItem(LOCALE_STORAGE_KEY);
    if (saved === 'en' || saved === 'ru' || saved === 'et') {
      return saved;
    }
    const navigatorLang = navigator.language.toLowerCase();
    if (navigatorLang.startsWith('ru')) return 'ru';
    if (navigatorLang.startsWith('et')) return 'et';
    return 'en';
  }
}