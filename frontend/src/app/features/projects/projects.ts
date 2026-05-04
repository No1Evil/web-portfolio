import { Component, inject, effect } from '@angular/core';
import { FontAwesomeModule  } from '@fortawesome/angular-fontawesome';
import { faCode } from '@fortawesome/free-solid-svg-icons';
import {ProjectService} from '../../core/services/project/project-service';
import {LanguageService} from '../../core/services/language/language-service';

@Component({
  selector: 'app-projects',
  imports: [FontAwesomeModule],
  templateUrl: './projects.html',
  styleUrl: './projects.css',
})
export class Projects {
  faCode = faCode;

  projectService = inject(ProjectService);
  langService = inject(LanguageService);
}
