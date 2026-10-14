import type { Routes } from '@angular/router';

export default [
  {
    path: 'education',
    title: 'Fjodor Tsumakov — Education',
    loadComponent: () => import('./pages/education.page').then((m) => m.EducationPage),
  },
] satisfies Routes;