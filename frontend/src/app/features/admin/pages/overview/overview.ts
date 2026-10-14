import { ChangeDetectionStrategy, Component, computed, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { I18nService } from '@core/i18n/i18n.service';
import { unwrap } from '@core/state/async-state';
import { StatCard } from '../../components/stat-card/stat-card';
import { ContactsStore } from '../../data/contacts.store';
import { ExperienceStore } from '../../data/experience.store';
import { SkillsStore } from '../../data/skills.store';

@Component({
  selector: 'app-admin-overview',
  imports: [StatCard, RouterLink],
  templateUrl: './overview.html',
  styleUrl: './overview.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class OverviewPage {
  private readonly i18n = inject(I18nService);
  private readonly skills = inject(SkillsStore);
  private readonly contacts = inject(ContactsStore);
  private readonly experience = inject(ExperienceStore);

  protected readonly dict = this.i18n.dictionary;

  protected readonly stats = computed(() => {
    const d = this.dict()['admin']['overview'];
    return [
      { label: d['statSkills'], value: String(unwrap(this.skills.userSkills())?.length ?? 0) },
      { label: d['statExperience'], value: String(unwrap(this.experience.experience())?.length ?? 0) },
      { label: d['statContacts'], value: String(unwrap(this.contacts.contacts())?.length ?? 0) },
    ];
  });

  protected readonly quickActions: readonly { label: string; path: string }[] = [
    { label: this.i18n.dictionary()['admin']['overview']['addProject'], path: '/admin/projects' },
    { label: this.i18n.dictionary()['admin']['overview']['addSkill'], path: '/admin/skills' },
    { label: this.i18n.dictionary()['admin']['overview']['addExperience'], path: '/admin/experience' },
    { label: this.i18n.dictionary()['admin']['overview']['addContact'], path: '/admin/contacts' },
  ];
}