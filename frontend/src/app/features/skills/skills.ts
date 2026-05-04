import { Component, inject } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faCode, faLayerGroup, faNetworkWired, faDatabase, faTools } from '@fortawesome/free-solid-svg-icons';
import { SkillService } from '../../core/services/skill/skill-service';
import {LanguageService} from '../../core/services/language/language-service';

@Component({
  selector: 'app-skills',
  imports: [FontAwesomeModule],
  templateUrl: './skills.html',
  styleUrl: './skills.css',
})
export class Skills {
  private icons = {
    'fa-code': faCode,
    'fa-layer-group': faLayerGroup,
    'fa-network-wired': faNetworkWired,
    'fa-database': faDatabase,
    'fa-tools': faTools
  };

  skillService = inject(SkillService)
  langService = inject(LanguageService)

  ngOnInit(){
    this.skillService.loadSkills();
  }

  getIcon(iconName: string): any {
    return this.icons[iconName as keyof typeof this.icons] || faCode;
  }
}
