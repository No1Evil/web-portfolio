import { Injectable, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import { SkillModel } from '../../models/skill/skill-model';

@Injectable({
  providedIn: 'root',
})
export class SkillService {

  private http = inject(HttpClient);
  private readonly API_URL = environment.apiUrl;

  skills = signal<SkillModel[] | null>(null);

  constructor() {

  }

  loadSkills(){
    this.http.get<SkillModel[]>(`${this.API_URL}/skills`)
      .subscribe(data => {
        this.skills.set(data);
        console.log('Skills data updated')
      })
  }
}
