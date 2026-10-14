import { ChangeDetectionStrategy, Component, computed, input } from '@angular/core';
import type { UserSkillResponse } from '@api/index';
import type { StackGroup } from '../../data/home.models';

@Component({
  selector: 'app-tech-stack-group',
  imports: [],
  templateUrl: './tech-stack-group.html',
  styleUrl: './tech-stack-group.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class TechStackGroup {

  readonly group = input.required<StackGroup>();

  protected readonly title = computed(() => {
    return this.group().category.name;
  });

  protected readonly hasIcon = (skill: UserSkillResponse): boolean =>
    !!skill.iconUrl && /^(\/|\.\/|https?:\/\/)/.test(skill.iconUrl);
}
