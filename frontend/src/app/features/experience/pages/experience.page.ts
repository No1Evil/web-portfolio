import { ChangeDetectionStrategy, Component, computed, inject } from '@angular/core';
import { I18nService } from '@core/i18n/i18n.service';
import { unwrap } from '@core/state/async-state';
import { formatPeriod } from '@core/util/dates';
import { ExperienceStore } from '../data/experience.store';

interface ExperienceEntry {
  id: string;
  title: string;
  company: string;
  location: string;
  period: string;
  description: string;
}

@Component({
  selector: 'app-experience-page',
  imports: [],
  templateUrl: './experience.page.html',
  styleUrl: './experience.page.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ExperiencePage {
  private readonly i18n = inject(I18nService);
  private readonly store = inject(ExperienceStore);

  protected readonly dict = this.i18n.dictionary;
  protected readonly locale = this.i18n.locale;
  protected readonly experience = this.store.experience;

  protected readonly entries = computed<ExperienceEntry[]>(() => {
    const presentLabel = this.dict()['experience']['present'];
    const items = unwrap(this.experience()) ?? [];
    return [...items]
      .sort((a, b) => compareDates(b.startDate, a.startDate))
      .map((item) => ({
        id: item.id ?? '',
        title: item.title ?? '',
        company: item.companyName ?? '',
        location: item.location ?? '',
        period: formatPeriod(item.startDate, item.endDate, item.present, presentLabel),
        description: localized(item.description, this.locale()),
      }));
  });

  protected subtitle(entry: ExperienceEntry): string {
    return [entry.company, entry.location].filter(Boolean).join(' · ');
  }
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