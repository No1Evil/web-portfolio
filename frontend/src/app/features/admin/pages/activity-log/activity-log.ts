import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { I18nService } from '@core/i18n/i18n.service';

@Component({
  selector: 'app-admin-activity-log',
  imports: [],
  templateUrl: './activity-log.html',
  styleUrl: './activity-log.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ActivityLogPage {
  private readonly i18n = inject(I18nService);
  protected readonly dict = this.i18n.dictionary;
}