import { Injectable, signal } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class LayoutService {
  isCollapsed= signal(false);

  toggleSidebar() {
    this.isCollapsed.update(val => !val);
  }
}
