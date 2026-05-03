export interface SkillItem {
  name: string;
  isPrimary: boolean;
}

export interface SkillModel {
  id: number;
  label: string;
  icon: string;
  skills: SkillItem[];
}
