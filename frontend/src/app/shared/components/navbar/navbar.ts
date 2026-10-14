import { ChangeDetectionStrategy, Component, computed, inject } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { I18nService } from '@core/i18n/i18n.service';
import { LanguageSwitcher } from '../language-switcher/language-switcher';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink, RouterLinkActive, LanguageSwitcher],
  templateUrl: './navbar.html',
  styleUrl: './navbar.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class Navbar {
  private readonly i18n = inject(I18nService);

  protected readonly dict = this.i18n.dictionary;

  protected readonly links = computed(() => {
    const nav = this.dict().nav;
    return [
      { path: '/', label: nav.home },
      { path: '/projects', label: nav.projects },
      { path: '/experience', label: nav.experience },
      { path: '/education', label: nav.education },
      { path: '/contact', label: nav.contact },
    ];
  });
}