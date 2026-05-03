import { Component, inject } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faEnvelope } from '@fortawesome/free-solid-svg-icons';
import { faTelegram, faLinkedin } from '@fortawesome/free-brands-svg-icons';
import { LanguageService } from '../../core/services/language/language-service';
import {ContactService} from '../../core/services/contact/contact-service';

@Component({
  selector: 'app-contact',
  imports: [FontAwesomeModule],
  templateUrl: './contact.html',
  styleUrl: './contact.css',
})
export class Contact {
  private icons = {
    'fa-envelope': faEnvelope,
    'fa-telegram': faTelegram,
    'fa-linkedin': faLinkedin,
  };

  langService = inject(LanguageService);
  contactService = inject(ContactService);

  getIcon(iconName: string): any {
    return this.icons[iconName as keyof typeof this.icons] || faEnvelope;
  }

  ngOnInit(){
    this.contactService.loadCards();
  }
}
