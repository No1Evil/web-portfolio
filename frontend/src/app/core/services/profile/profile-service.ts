import { Injectable, inject, signal, effect } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LanguageService } from '../language/language-service';
import { environment } from '../../../../environments/environment';
import { ProfileModel } from '../../models/profile/profile-model';

@Injectable({
  providedIn: 'root',
})
export class ProfileService {

  private http = inject(HttpClient);

  private langService = inject(LanguageService);

  private readonly API_URL = environment.apiUrl;

  profile = signal<ProfileModel | null>(null);

  constructor() {
    effect(() => {
      console.log('Language changed to: ', this.langService.currentLang())
      this.loadProfile()
    });
  }

  loadProfile(){
    const lang = this.langService.currentLang();

    this.http.get<ProfileModel>(`${this.API_URL}/profile?lang=${lang}`)
      .subscribe(data => {
        this.profile.set(data)
        console.log('Profile data updated for lang:', lang)
      })
  }
}
