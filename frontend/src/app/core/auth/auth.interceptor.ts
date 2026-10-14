import { inject } from '@angular/core';
import {
  HttpErrorResponse,
  type HttpHandlerFn,
  type HttpInterceptorFn,
  type HttpRequest,
} from '@angular/common/http';
import { catchError, throwError } from 'rxjs';
import { AuthService } from './auth.service';

const AUTH_PREFIXES = ['/api/v1/admin/', '/api/v1/auth/me'];

export const authInterceptor: HttpInterceptorFn = (req: HttpRequest<unknown>, next: HttpHandlerFn) => {
  const auth = inject(AuthService);
  return next(req).pipe(
    catchError((error: unknown) => {
      if (
        error instanceof HttpErrorResponse &&
        error.status === 401 &&
        AUTH_PREFIXES.some((prefix) => req.url.includes(prefix))
      ) {
        auth.reset();
      }
      return throwError(() => error);
    }),
  );
};