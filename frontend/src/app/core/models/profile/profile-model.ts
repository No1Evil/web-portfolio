import {DetailModel} from './detail-model';

export interface ProfileModel {
  id: number;

  name: string;
  available: boolean;
  availabilityText: string;
  subtitle: string;
  description: string[];

  stats: DetailModel[];
  info: DetailModel[];
}
