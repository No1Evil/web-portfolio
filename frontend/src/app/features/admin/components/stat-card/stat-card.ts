import { ChangeDetectionStrategy, Component, input } from '@angular/core';

@Component({
  selector: 'app-admin-stat-card',
  imports: [],
  template: `
    <div class="stat-card">
      <span class="stat-card__label">{{ label() }}</span>
      <span class="stat-card__value">{{ value() }}</span>
    </div>
  `,
  styleUrl: './stat-card.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class StatCard {
  readonly label = input.required<string>();
  readonly value = input.required<string>();
}