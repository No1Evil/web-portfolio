import { ChangeDetectionStrategy, Component, inject, signal } from '@angular/core';
import { NavigationEnd, Router, RouterLink } from '@angular/router';
import { filter } from 'rxjs';
import { AuthService } from '@core/auth/auth.service';
import { I18nService } from '@core/i18n/i18n.service';
import type { AdminSectionKey } from '../../data/admin.models';
import { AdminLocaleSwitcher } from '../../components/admin-locale-switcher/admin-locale-switcher';
import { LanguageSwitcher } from '@shared/components/language-switcher/language-switcher';

@Component({
  selector: 'app-admin-topbar',
  imports: [RouterLink, LanguageSwitcher, AdminLocaleSwitcher],
  templateUrl: './admin-topbar.html',
  styleUrl: './admin-topbar.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AdminTopbar {
  private readonly i18n = inject(I18nService);
  private readonly auth = inject(AuthService);
  private readonly router = inject(Router);

  protected readonly dict = this.i18n.dictionary;
  protected readonly authenticated = this.auth.authenticated;
  protected readonly user = this.auth.user;
  protected readonly localeCode = this.i18n.localeCode;

  protected readonly titleKey = signal<AdminSectionKey>('overview');

  constructor() {
    this.router.events
      .pipe(filter((event): event is NavigationEnd => event instanceof NavigationEnd))
      .subscribe(() => this.updateTitle());
  }

  private updateTitle(): void {
    const data = this.router.routerState.snapshot.root.firstChild?.routeConfig?.data;
    const key = data?.['titleKey'] as AdminSectionKey | undefined;
    this.titleKey.set(key ?? 'overview');
  }

  protected signOut(): void {
    this.auth.logout().subscribe(() => this.router.navigate(['/admin/login']));
  }
}