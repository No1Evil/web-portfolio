import type { Routes } from '@angular/router';
import contactRoutes from '@features/contact/contact.routes';
import educationRoutes from '@features/education/education.routes';
import experienceRoutes from '@features/experience/experience.routes';
import homeRoutes from '@features/home/home.routes';
import { PublicShell } from '@shared/layout/public-shell/public-shell';

export const routes: Routes = [
  {
    path: '',
    component: PublicShell,
    children: [...homeRoutes, ...educationRoutes, ...experienceRoutes, ...contactRoutes],
  },
  {
    path: 'admin',
    loadChildren: () => import('@features/admin/admin.routes'),
  },
];