import { Injectable, signal, inject, effect } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LanguageService {
  private http = inject(HttpClient);

  readonly currentLang = signal<string>(localStorage.getItem('lang') || 'en');

  private translations = signal<Record<string, string>>({});

  constructor() {
    effect(async () => {
      const lang = this.currentLang();
      await this.loadTranslations(lang);
    }, { allowSignalWrites: true });
  }

  private async loadTranslations(lang: string) {
    try {
      const data = await firstValueFrom(
        this.http.get<Record<string, string>>(`assets/i18n/${lang}.json`)
      );
      this.translations.set(data);
      localStorage.setItem('lang', lang);
    } catch (err) {
      console.error(`Could not load translations for ${lang}`, err);
    }
  }

  changeLang(lang: string) {
    this.currentLang.set(lang);
  }

  translate(key: string): string {
    return this.translations()[key] || key;
  }
}
