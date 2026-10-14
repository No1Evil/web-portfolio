import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { I18nService } from '@core/i18n/i18n.service';
import { AdminSidebar } from '../admin-sidebar/admin-sidebar';
import { AdminTopbar } from '../admin-topbar/admin-topbar';

@Component({
  selector: 'app-admin-shell',
  imports: [RouterOutlet, AdminSidebar, AdminTopbar],
  templateUrl: './admin-shell.html',
  styleUrl: './admin-shell.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AdminShell {
  private readonly i18n = inject(I18nService);
  protected readonly dict = this.i18n.dictionary;
}