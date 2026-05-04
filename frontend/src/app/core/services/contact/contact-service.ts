import { Injectable, inject, signal, effect } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LanguageService } from '../language/language-service';
import { environment } from '../../../../environments/environment';
import { ContactModel } from '../../models/contact/contact-model';


@Injectable({
  providedIn: 'root',
})
export class ContactService {
  private http = inject(HttpClient);

  private langService = inject(LanguageService);

  private readonly API_URL = environment.apiUrl;

  contacts = signal<ContactModel[] | null>(null);

  constructor() {
    effect(() => {
      this.loadCards();
    });
  }

  loadCards(){
    const lang = this.langService.currentLang();

    this.http.get<ContactModel[]>(`${this.API_URL}/contacts?lang=${lang}`)
    .subscribe(data => {
      this.contacts.set(data)
      console.log('Contacts data updated for lang:', lang)
    })
  }
}
