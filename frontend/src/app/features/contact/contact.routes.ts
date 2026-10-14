import type { Routes } from '@angular/router';

export default [
  {
    path: 'contact',
    title: 'Fjodor Tsumakov — Contact',
    loadComponent: () => import('./pages/contact.page').then((m) => m.ContactPage),
  },
] satisfies Routes;