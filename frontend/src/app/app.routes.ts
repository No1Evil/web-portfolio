import { Routes } from '@angular/router';
import {About} from './features/about/about';
import {Projects} from './features/projects/projects';
import {Skills} from './features/skills/skills';
import {Contact} from './features/contact/contact';

export const routes: Routes = [
  { path: '', component: About },
  { path: 'about', component: About },
  { path: 'projects', component: Projects },
  { path: 'skills', component: Skills },
  { path: 'contact', component: Contact }
];
