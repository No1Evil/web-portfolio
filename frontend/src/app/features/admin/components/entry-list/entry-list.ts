import { ChangeDetectionStrategy, Component, inject, input, output } from '@angular/core';
import { I18nService } from '@core/i18n/i18n.service';
import type { EntryRow } from '../../data/admin.models';

@Component({
  selector: 'app-admin-entry-list',
  imports: [],
  templateUrl: './entry-list.html',
  styleUrl: './entry-list.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class EntryList {
  private readonly i18n = inject(I18nService);
  protected readonly dict = this.i18n.dictionary;

  readonly rows = input.required<EntryRow[]>();

  readonly edit = output<EntryRow>();
  readonly delete = output<EntryRow>();
}