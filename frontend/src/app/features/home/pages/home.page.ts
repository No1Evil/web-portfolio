import { ChangeDetectionStrategy, Component, computed, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { I18nService } from '@core/i18n/i18n.service';
import { unwrap } from '@core/state/async-state';
import { HomeStore } from '../data/home.store';
import { TechStackGroup } from '../components/tech-stack-group/tech-stack-group';

@Component({
  selector: 'app-home-page',
  imports: [RouterLink, TechStackGroup],
  templateUrl: './home.page.html',
  styleUrl: './home.page.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class HomePage {
  private readonly i18n = inject(I18nService);
  private readonly store = inject(HomeStore);

  protected readonly dict = this.i18n.dictionary;
  protected readonly locale = this.i18n.locale;
  protected readonly hero = this.store.hero;
  protected readonly stack = this.store.stack;

  protected readonly fullName = computed(() => {
    const summary = unwrap(this.hero());
    return summary ? `${summary.firstName} ${summary.lastName}`.trim() : '';
  });

  protected readonly description = computed(() => {
    const summary = unwrap(this.hero());
    if (!summary) return '';
    const description = summary.description ?? {};
    return description[this.locale()] ?? description['en'] ?? '';
  });
}