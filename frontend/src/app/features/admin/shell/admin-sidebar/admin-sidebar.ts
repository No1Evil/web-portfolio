import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { I18nService } from '@core/i18n/i18n.service';
import type { AdminSectionKey } from '../../data/admin.models';

const NAV_ITEMS: readonly { key: AdminSectionKey; path: string }[] = [
  { key: 'overview', path: 'overview' },
  { key: 'content', path: 'content' },
  { key: 'skills', path: 'skills' },
  { key: 'projects', path: 'projects' },
  { key: 'experience', path: 'experience' },
  { key: 'education', path: 'education' },
  { key: 'contacts', path: 'contacts' },
  { key: 'activityLog', path: 'activity-log' },
];

@Component({
  selector: 'app-admin-sidebar',
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './admin-sidebar.html',
  styleUrl: './admin-sidebar.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AdminSidebar {
  private readonly i18n = inject(I18nService);
  protected readonly dict = this.i18n.dictionary;
  protected readonly items = NAV_ITEMS;
  protected readonly crumb = this.i18n.dictionary;
}