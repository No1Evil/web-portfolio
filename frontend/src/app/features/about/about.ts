import { Component, inject, computed } from '@angular/core';
import { ProfileService } from '../../core/services/profile/profile-service';
import {LanguageService} from '../../core/services/language/language-service';

@Component({
  selector: 'app-about',
  imports: [],
  templateUrl: './about.html',
  styleUrl: './about.css',
})
export class About {

  profileService = inject(ProfileService);
  langService = inject(LanguageService);

  infoList = computed(() => {
    const profile = this.profileService.profile();
    return profile ? profile.info : [];
  });

  statsList = computed(() => {
    const profile = this.profileService.profile();
    return profile ? profile.stats : [];
  });

  descriptionList = computed(() => {
    const profile = this.profileService.profile();
      return profile ? profile.description : [];
  });

  ngOnInit(){
    this.profileService.loadProfile();
  }
}
