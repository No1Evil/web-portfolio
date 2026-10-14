import { ChangeDetectionStrategy, Component, inject, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { finalize } from 'rxjs';
import { AuthService } from '@core/auth/auth.service';
import { I18nService } from '@core/i18n/i18n.service';

@Component({
  selector: 'app-admin-login',
  imports: [FormsModule, RouterLink],
  templateUrl: './login.html',
  styleUrl: './login.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class LoginPage {
  private readonly i18n = inject(I18nService);
  private readonly auth = inject(AuthService);
  private readonly router = inject(Router);

  protected readonly dict = this.i18n.dictionary;

  protected readonly username = signal('');
  protected readonly password = signal('');
  protected readonly busy = signal(false);
  protected readonly error = signal(false);

  protected submit(): void {
    if (this.busy()) return;
    this.error.set(false);
    this.busy.set(true);
    this.auth
      .login(this.username(), this.password())
      .pipe(finalize(() => this.busy.set(false)))
      .subscribe({
        next: () => this.router.navigate(['/admin']),
        error: (err: unknown) => {
          if (err instanceof HttpErrorResponse) {
            this.error.set(true);
          }
        },
      });
  }
}