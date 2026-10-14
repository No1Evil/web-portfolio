import type { Routes } from '@angular/router';
import { authGuard } from '@core/auth/auth.guard';

export default [
  {
    path: 'login',
    title: 'Admin — Sign in',
    loadComponent: () => import('./pages/login/login').then((m) => m.LoginPage),
  },
  {
    path: '',
    canActivate: [authGuard],
    loadComponent: () => import('./shell/admin-shell/admin-shell').then((m) => m.AdminShell),
    children: [
      { path: '', redirectTo: 'overview', pathMatch: 'full' },
      {
        path: 'overview',
        data: { titleKey: 'overview' },
        loadComponent: () => import('./pages/overview/overview').then((m) => m.OverviewPage),
      },
      {
        path: 'content',
        data: { titleKey: 'content' },
        loadComponent: () => import('./pages/content/content').then((m) => m.ContentPage),
      },
      {
        path: 'skills',
        data: { titleKey: 'skills' },
        loadComponent: () => import('./pages/skills/skills').then((m) => m.SkillsPage),
      },
      {
        path: 'projects',
        data: { titleKey: 'projects' },
        loadComponent: () => import('./pages/projects/projects').then((m) => m.ProjectsPage),
      },
      {
        path: 'experience',
        data: { titleKey: 'experience' },
        loadComponent: () => import('./pages/experience/experience').then((m) => m.ExperiencePage),
      },
      {
        path: 'education',
        data: { titleKey: 'education' },
        loadComponent: () => import('./pages/education/education').then((m) => m.EducationPage),
      },
      {
        path: 'contacts',
        data: { titleKey: 'contacts' },
        loadComponent: () => import('./pages/contacts/contacts').then((m) => m.ContactsPage),
      },
      {
        path: 'activity-log',
        data: { titleKey: 'activityLog' },
        loadComponent: () => import('./pages/activity-log/activity-log').then((m) => m.ActivityLogPage),
      },
    ],
  },
] satisfies Routes;