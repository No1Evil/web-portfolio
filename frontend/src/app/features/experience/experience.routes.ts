import type { Routes } from '@angular/router';

export default [
  {
    path: 'experience',
    title: 'Fjodor Tsumakov — Experience',
    loadComponent: () => import('./pages/experience.page').then((m) => m.ExperiencePage),
  },
] satisfies Routes;