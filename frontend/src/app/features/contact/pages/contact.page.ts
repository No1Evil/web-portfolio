import { ChangeDetectionStrategy, Component, computed, inject } from '@angular/core';
import { I18nService } from '@core/i18n/i18n.service';
import { unwrap } from '@core/state/async-state';
import type { UserContactResponse } from '@api/index';
import { ContactStore } from '../data/contact.store';

@Component({
  selector: 'app-contact-page',
  imports: [],
  templateUrl: './contact.page.html',
  styleUrl: './contact.page.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ContactPage {
  private readonly i18n = inject(I18nService);
  private readonly store = inject(ContactStore);

  protected readonly dict = this.i18n.dictionary;
  protected readonly contacts = this.store.contacts;

  protected readonly items = computed<UserContactResponse[]>(() => unwrap(this.contacts()) ?? []);

  protected hasIcon(contact: UserContactResponse): boolean {
    return Boolean(contact.iconUrl);
  }
}