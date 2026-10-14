import { HttpErrorResponse, HttpClient } from '@angular/common/http';
import { computed, inject, Injectable, signal } from '@angular/core';
import { catchError, finalize, firstValueFrom, of, tap, type Observable } from 'rxjs';
import type { UserResponse } from '@api/index';
import { API_BASE_PATH } from '@core/config/api';

const USER_STORAGE_KEY = 'admin-user';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly http = inject(HttpClient);
  private readonly userSignal = signal<UserResponse | null>(this.readStoredUser());

  readonly user = this.userSignal.asReadonly();
  readonly authenticated = computed(() => this.userSignal() !== null);

  login(username: string, password: string): Observable<UserResponse> {
    return this.http
      .post<UserResponse>(`${API_BASE_PATH}/auth/login`, { username, password }, { withCredentials: true })
      .pipe(
        tap((user) => {
          this.userSignal.set(user);
          localStorage.setItem(USER_STORAGE_KEY, JSON.stringify(user));
        }),
      );
  }

  logout(): Observable<unknown> {
    return this.http.post(`${API_BASE_PATH}/auth/logout`, {}, { withCredentials: true }).pipe(
      catchError(() => of(null)),
      finalize(() => this.reset()),
    );
  }

  /** Verifies the session against the backend via the `/me` endpoint. */
  async ensureAuthenticated(): Promise<boolean> {
    try {
      const user = await firstValueFrom(
        this.http.get<UserResponse>(`${API_BASE_PATH}/auth/me`, { withCredentials: true }),
      );
      this.userSignal.set(user);
      localStorage.setItem(USER_STORAGE_KEY, JSON.stringify(user));
      return true;
    } catch (error) {
      if (error instanceof HttpErrorResponse && (error.status === 401 || error.status === 403)) {
        this.reset();
      }
      return false;
    }
  }

  reset(): void {
    this.userSignal.set(null);
    localStorage.removeItem(USER_STORAGE_KEY);
  }

  private readStoredUser(): UserResponse | null {
    const raw = localStorage.getItem(USER_STORAGE_KEY);
    if (!raw) return null;
    try {
      const parsed = JSON.parse(raw) as UserResponse;
      return parsed && typeof parsed === 'object' ? parsed : null;
    } catch {
      return null;
    }
  }
}