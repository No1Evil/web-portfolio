import { ChangeDetectionStrategy, Component, computed, inject } from '@angular/core';
import { I18nService } from '@core/i18n/i18n.service';
import { unwrap } from '@core/state/async-state';
import { formatPeriod } from '@core/util/dates';
import { EducationStore } from '../data/education.store';

interface EducationEntry {
  id: string;
  title: string;
  location: string;
  period: string;
  description: string;
}

@Component({
  selector: 'app-education-page',
  imports: [],
  templateUrl: './education.page.html',
  styleUrl: './education.page.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class EducationPage {
  private readonly i18n = inject(I18nService);
  private readonly store = inject(EducationStore);

  protected readonly dict = this.i18n.dictionary;
  protected readonly locale = this.i18n.locale;
  protected readonly education = this.store.education;

  protected readonly entries = computed<EducationEntry[]>(() => {
    const presentLabel = this.dict()['education']['present'];
    const items = unwrap(this.education()) ?? [];
    return [...items]
      .sort((a, b) => compareDates(b.startDate, a.startDate))
      .map((item) => ({
        id: item.id ?? '',
        title: item.title ?? '',
        location: item.location ?? '',
        period: formatPeriod(item.startDate, item.endDate, item.present, presentLabel),
        description: localized(item.description, this.locale()),
      }));
  });
}

function localized(map: Record<string, string> | undefined, locale: string): string {
  if (!map) return '';
  return map[locale] ?? map['en'] ?? '';
}

function compareDates(a: string | Date | null | undefined, b: string | Date | null | undefined): number {
  const timeA = a ? new Date(a).getTime() : 0;
  const timeB = b ? new Date(b).getTime() : 0;
  return timeA - timeB;
}