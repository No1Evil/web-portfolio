import type { SkillCategoryAdminResponse, UserSkillResponse } from '@api/index';

export type AdminSectionKey =
  | 'overview'
  | 'content'
  | 'skills'
  | 'projects'
  | 'education'
  | 'experience'
  | 'contacts'
  | 'activityLog';

export interface SkillGroup {
  category: SkillCategoryAdminResponse;
  skills: UserSkillResponse[];
}

export interface EntryRow {
  id: string | number;
  title: string;
  subtitle?: string;
  meta?: string;
}

export interface EducationPathPayload {
  title: string;
  location: string;
  description: Record<string, string>;
  present: boolean;
  startDate?: string;
  endDate?: string;
}

export interface ExperiencePathPayload {
  title: string;
  companyName: string;
  location: string;
  description: Record<string, string>;
  present: boolean;
  startDate?: string;
  endDate?: string;
}

export const CONTACT_TYPES = [
  'Telegram',
  'Email',
  'GitHub',
  'LinkedIn',
  'X / Twitter',
  'Website',
] as const;