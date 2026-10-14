import { ChangeDetectionStrategy, Component, computed, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { I18nService } from '@core/i18n/i18n.service';
import { unwrap } from '@core/state/async-state';
import type { SkillCategoryAdminResponse, UserSkillResponse } from '@api/index';
import { SkillsStore } from '../../data/skills.store';

@Component({
  selector: 'app-admin-skills',
  imports: [FormsModule],
  templateUrl: './skills.html',
  styleUrl: './skills.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SkillsPage {
  private readonly i18n = inject(I18nService);
  private readonly skills = inject(SkillsStore);

  protected readonly dict = this.i18n.dictionary;

  protected readonly categoryList = computed(() => unwrap(this.skills.categories()) ?? []);

  protected readonly groupList = computed(() => unwrap(this.skills.groups()) ?? []);

  protected readonly categoryId = signal<number | null>(null);
  protected readonly name = signal('');
  protected readonly editing = signal<UserSkillResponse | null>(null);

  protected readonly categoryName = signal('');
  protected readonly editingCategory = signal<SkillCategoryAdminResponse | null>(null);

  protected add(): void {
    const categoryId = this.editing()?.categoryId ?? this.categoryId();
    const name = this.name().trim();
    if (categoryId == null || name === '') return;
    if (this.editing()) {
      this.skills.update(this.editing()!.id ?? 0, name);
    } else {
      this.skills.add(categoryId, name);
    }
    this.name.set('');
    this.editing.set(null);
  }

  protected startEdit(skill: UserSkillResponse): void {
    this.editing.set(skill);
    this.categoryId.set(skill.categoryId ?? null);
    this.name.set(skill.name ?? '');
  }

  protected cancelEdit(): void {
    this.editing.set(null);
    this.name.set('');
  }

  protected remove(skill: UserSkillResponse): void {
    if (skill.id !== undefined) {
      this.skills.remove(skill.id);
    }
  }

  protected trackSkill(_index: number, skill: UserSkillResponse): number {
    return skill.id ?? 0;
  }

  protected parseCategory(value: string): number | null {
    return value === '' ? null : Number(value);
  }

  protected saveCategory(): void {
    const name = this.categoryName().trim();
    if (name === '') return;
    if (this.editingCategory()) {
      this.skills.updateCategory(this.editingCategory()!.id ?? 0, { name });
    } else {
      this.skills.addCategory({ name });
    }
    this.categoryName.set('');
    this.editingCategory.set(null);
  }

  protected startEditCategory(category: SkillCategoryAdminResponse): void {
    this.editingCategory.set(category);
    this.categoryName.set(category.name ?? '');
  }

  protected cancelEditCategory(): void {
    this.editingCategory.set(null);
    this.categoryName.set('');
  }

  protected removeCategory(category: SkillCategoryAdminResponse): void {
    if (category.id !== undefined) {
      this.skills.removeCategory(category.id);
    }
  }

  protected trackCategory(_index: number, category: SkillCategoryAdminResponse): number {
    return category.id ?? 0;
  }
}