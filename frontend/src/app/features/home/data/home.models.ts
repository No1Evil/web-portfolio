import type {
  SkillCategoryUserResponse,
  UserSkillResponse,
  UserSummaryResponse,
} from '@api/index';
import type { AsyncState } from '@core/state/async-state';

export interface StackGroup {
  category: SkillCategoryUserResponse;
  skills: UserSkillResponse[];
}

export type HeroState = AsyncState<UserSummaryResponse>;
export type StackState = AsyncState<StackGroup[]>;