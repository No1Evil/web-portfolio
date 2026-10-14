import type { Routes } from '@angular/router';

export default [
  {
    path: '',
    title: 'Fjodor Tsumakov — Software Engineer',
    loadComponent: () => import('./pages/home.page').then((m) => m.HomePage),
  },
] satisfies Routes;