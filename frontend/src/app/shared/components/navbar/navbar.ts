import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { LayoutService } from '../../../core/services/layout/layout-service';
import { LanguageService } from '../../../core/services/language/language-service';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import {
  faUser,
  faCode,
  faProjectDiagram,
  faEnvelope
} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-navbar',
  imports: [RouterLink, CommonModule, FontAwesomeModule],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})
export class Navbar {
  layoutService = inject(LayoutService);
  langService = inject(LanguageService);

  faUser = faUser;
  faCode = faCode;
  faProjectDiagram = faProjectDiagram;
  faEnvelope = faEnvelope;
}
