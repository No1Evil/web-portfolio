import {SkillItem} from '../skill/skill-model';

export interface ProjectModel {
  id: number;
  title: string;
  description: string;
  tags: SkillItem[];
  link: string;
}
