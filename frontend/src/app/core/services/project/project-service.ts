import { Injectable, inject, signal, effect } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import { ProjectModel } from '../../models/project/project-model';

@Injectable({
  providedIn: 'root',
})
export class ProjectService {

  private http = inject(HttpClient);

  private readonly API_URL = environment.apiUrl;

  projects = signal<ProjectModel[] | null>(null);

  constructor() {
    effect(() => {
      this.loadProjects()
    });
  }

  loadProjects(){
    this.http.get<ProjectModel[]>(`${this.API_URL}/projects`)
    .subscribe(data => {
      this.projects.set(data)
      console.log('Project data updated.')
    })
  }
}
